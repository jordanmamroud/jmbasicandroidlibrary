package fragments;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.ViewGroup;

import com.example.jordan.jmbasicandroidlibrary.R;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.annotations.NonNull;
import io.reactivex.schedulers.Schedulers;
import utilities.RxHelper;


/**
 * Created by Jordan on 7/27/2017.
 */

public class FragmentActivityController {

    private FragmentManager fragManager ;
    private ViewGroup fragmentContainer ;

     //default values ;
    int animIn = R.anim.fade_in ;
    int animOut = R.anim.fade_out;
    int backstackLimit = 2;

    public FragmentActivityController(FragmentManager fragManager , ViewGroup fragmentContainer ) {
        this.fragManager = fragManager;
        this.fragmentContainer = fragmentContainer ;
    }

    public FragmentTransaction createFragmentTransaction(   Fragment fragmentToAdd, String newFragTag  , String backstackTag    ){

        if(fragmentContainer!=null) fragmentContainer.removeAllViewsInLayout();

        FragmentTransaction transaction =   fragManager.beginTransaction();

        transaction.setCustomAnimations( animIn, animOut    , animIn    , animOut   );

        if(backstackTag == null) return  openFirstFragment(transaction, fragmentToAdd)  ;

        if(     hasUnusedFragments()    )   {
            Observable observable =   RxHelper.createObservable(( ObservableEmitter<Object> e    )-> {    clearUnusedFragments(); }  ) ;
            RxHelper.subscribe(observable);
        }

        transaction.addToBackStack(newFragTag);

        transaction.replace(fragmentContainer.getId(), fragmentToAdd, newFragTag);

//        if( isBackStackFull()  ) popBackStackProperly();

        return transaction ;
    }

    private boolean hasUnusedFragments(){
        return  fragManager.getFragments()!= null && fragManager.getFragments().size() > backstackLimit + 1 ;
    }

    private void clearUnusedFragments(){
        List<Fragment> fragmentList = fragManager.getFragments();
        System.out.println(fragmentList.size()) ;
        for(int i = 0;  i   < fragmentList.size() ; i++){
            Fragment fragment  = fragManager.getFragments().get(i) ;
            if(fragment == null)
                break;
            if(   i > backstackLimit  ){
                fragManager.beginTransaction().remove(  fragment  ).commit();
                fragmentList.remove(i);
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
                System.out.println( entry.getId() );
                // this does not remove first entry this pops off just added fragment ,
//                fragManager.popBackStack(entry.getId(), FragmentManager.POP_BACK_STACK_INCLUSIVE);
                fragManager.popBackStack( );
            }
        }
    }

    private boolean isBackStackFull(){ return  fragManager.getBackStackEntryCount() >= backstackLimit ; }

    public void setAnimIn(int animIn) { this.animIn = animIn; }

    public void setAnimOut(int animOut) { this.animOut = animOut; }


}
