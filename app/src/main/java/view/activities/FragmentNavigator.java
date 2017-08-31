package view.activities;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.ViewGroup;

import com.example.jordan.jmbasicandroidlibrary.R;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import utilities.RxHelper;


/**
 * Created by Jordan on 7/27/2017.
 */
// todo this class needs a lot of work but will be very useful.
public class FragmentNavigator {

    private FragmentManager fragManager ;
    private ViewGroup fragmentContainer ;

     //default values ;
    int animIn = R.anim.fade_in ;
    int animOut = R.anim.fade_out ;
    int backstackLimit = 2 ;

    public FragmentNavigator(FragmentManager fragManager , ViewGroup fragmentContainer ) {
        this.fragManager = fragManager;
        this.fragmentContainer = fragmentContainer ;
    }

    public FragmentTransaction createFragmentTransaction(   Fragment fragmentToAdd, String newFragTag  , String backstackTag    ){

        if( fragmentContainer   != null  )
            fragmentContainer.removeAllViewsInLayout();

        FragmentTransaction transaction =   fragManager.beginTransaction();

        transaction.setCustomAnimations( animIn, animOut    , animIn    , animOut   );

        if(backstackTag == null)
            return  openFirstFragment(transaction, fragmentToAdd)  ;

        if( fragManager.getBackStackEntryCount() < 2 )
            transaction.addToBackStack(backstackTag);

        transaction.replace(fragmentContainer.getId(), fragmentToAdd, newFragTag);

        return transaction ;
    }

    private boolean hasUnusedFragments(){
        return  fragManager.getFragments()!= null && fragManager.getFragments().size() > backstackLimit + 1 ;
    }

    private void clearUnusedFragments(){
        List<Fragment> fragmentList = fragManager.getFragments();

        for(int i = 0;  i   < fragmentList.size() ; i++){
            Fragment fragment  = fragManager.getFragments().get(0) ;
            if(fragment == null){
                System.out.println("null for some reason");
                break;
            }

            if(   i > backstackLimit  ){
                System.out.println("being removed");
                fragManager.beginTransaction().remove(  fragment  ).commit();
                fragmentList.remove(0);
            }
        }
    }

    private FragmentTransaction openFirstFragment(FragmentTransaction transaction , Fragment fragmentToAdd) {
        return transaction.replace(     fragmentContainer.getId()  , fragmentToAdd     );
    }

    // doing it this way because other way give problems without add flags for inclusive
    private void popBackStackProperly(){

        if(fragManager.getBackStackEntryCount() > 0 ) {
            FragmentManager.BackStackEntry entry = fragManager.getBackStackEntryAt(0);

            if (entry != null)  {

                // this does not remove first entry this pops off just added fragment ,
//                fragManager.popBackStack(entry.getId(), FragmentManager.POP_BACK_STACK_INCLUSIVE);
                fragManager.popBackStack( );
            }
        }
    }

    public void setBackstackLimit (int backstackLimit){
        this.backstackLimit = backstackLimit ;
    }

    private boolean isBackStackFull(){ return  fragManager.getBackStackEntryCount() >= backstackLimit ; }

    public void setAnimIn(int animIn) { this.animIn = animIn; }

    public void setAnimOut(int animOut) { this.animOut = animOut; }


}
