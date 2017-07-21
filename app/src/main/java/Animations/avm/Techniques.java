
/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2014 daimajia
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package Animations.avm;


import Animations.avm.attention.BounceAnimator;
import Animations.avm.attention.FlashAnimator;
import Animations.avm.attention.PulseAnimator;
import Animations.avm.attention.RubberBandAnimator;
import Animations.avm.attention.ShakeAnimator;
import Animations.avm.attention.StandUpAnimator;
import Animations.avm.attention.SwingAnimator;
import Animations.avm.attention.TadaAnimator;
import Animations.avm.attention.WaveAnimator;
import Animations.avm.attention.WobbleAnimator;
import Animations.avm.bouncing_entrances.BounceInAnimator;
import Animations.avm.bouncing_entrances.BounceInDownAnimator;
import Animations.avm.bouncing_entrances.BounceInLeftAnimator;
import Animations.avm.bouncing_entrances.BounceInRightAnimator;
import Animations.avm.bouncing_entrances.BounceInUpAnimator;
import Animations.avm.fading_entrances.FadeInAnimator;
import Animations.avm.fading_entrances.FadeInDownAnimator;
import Animations.avm.fading_entrances.FadeInLeftAnimator;
import Animations.avm.fading_entrances.FadeInRightAnimator;
import Animations.avm.fading_entrances.FadeInUpAnimator;
import Animations.avm.fading_exits.FadeOutAnimator;
import Animations.avm.fading_exits.FadeOutDownAnimator;
import Animations.avm.fading_exits.FadeOutLeftAnimator;
import Animations.avm.fading_exits.FadeOutRightAnimator;
import Animations.avm.fading_exits.FadeOutUpAnimator;
import Animations.avm.flippers.FlipInXAnimator;
import Animations.avm.flippers.FlipInYAnimator;
import Animations.avm.flippers.FlipOutXAnimator;
import Animations.avm.flippers.FlipOutYAnimator;
import Animations.avm.rotating_entrances.RotateInAnimator;
import Animations.avm.rotating_entrances.RotateInDownLeftAnimator;
import Animations.avm.rotating_entrances.RotateInDownRightAnimator;
import Animations.avm.rotating_entrances.RotateInUpLeftAnimator;
import Animations.avm.rotating_entrances.RotateInUpRightAnimator;
import Animations.avm.rotating_exits.RotateOutAnimator;
import Animations.avm.rotating_exits.RotateOutDownLeftAnimator;
import Animations.avm.rotating_exits.RotateOutDownRightAnimator;
import Animations.avm.rotating_exits.RotateOutUpLeftAnimator;
import Animations.avm.rotating_exits.RotateOutUpRightAnimator;
import Animations.avm.sliders.SlideInDownAnimator;
import Animations.avm.sliders.SlideInLeftAnimator;
import Animations.avm.sliders.SlideInRightAnimator;
import Animations.avm.sliders.SlideInUpAnimator;
import Animations.avm.sliders.SlideOutDownAnimator;
import Animations.avm.sliders.SlideOutLeftAnimator;
import Animations.avm.sliders.SlideOutRightAnimator;
import Animations.avm.sliders.SlideOutUpAnimator;
import Animations.avm.specials.HingeAnimator;
import Animations.avm.specials.RollInAnimator;
import Animations.avm.specials.RollOutAnimator;
import Animations.avm.specials.in.DropOutAnimator;
import Animations.avm.specials.in.LandingAnimator;
import Animations.avm.specials.out.TakingOffAnimator;
import Animations.avm.zooming_entrances.ZoomInAnimator;
import Animations.avm.zooming_entrances.ZoomInDownAnimator;
import Animations.avm.zooming_entrances.ZoomInLeftAnimator;
import Animations.avm.zooming_entrances.ZoomInRightAnimator;
import Animations.avm.zooming_entrances.ZoomInUpAnimator;
import Animations.avm.zooming_exits.ZoomOutAnimator;
import Animations.avm.zooming_exits.ZoomOutDownAnimator;
import Animations.avm.zooming_exits.ZoomOutLeftAnimator;
import Animations.avm.zooming_exits.ZoomOutRightAnimator;
import Animations.avm.zooming_exits.ZoomOutUpAnimator;

public enum Techniques {

    DropOut(DropOutAnimator.class),
    Landing(LandingAnimator.class),
    TakingOff(TakingOffAnimator.class),

    Flash(FlashAnimator.class),
    Pulse(PulseAnimator.class),
    RubberBand(RubberBandAnimator.class),
    Shake(ShakeAnimator.class),
    Swing(SwingAnimator.class),
    Wobble(WobbleAnimator.class),
    Bounce(BounceAnimator.class),
    Tada(TadaAnimator.class),
    StandUp(StandUpAnimator.class),
    Wave(WaveAnimator.class),

    Hinge(HingeAnimator.class),
    RollIn(RollInAnimator.class),
    RollOut(RollOutAnimator.class),

    BounceIn(BounceInAnimator.class),
    BounceInDown(BounceInDownAnimator.class),
    BounceInLeft(BounceInLeftAnimator.class),
    BounceInRight(BounceInRightAnimator.class),
    BounceInUp(BounceInUpAnimator.class),

    FadeIn(FadeInAnimator.class),
    FadeInUp(FadeInUpAnimator.class),
    FadeInDown(FadeInDownAnimator.class),
    FadeInLeft(FadeInLeftAnimator.class),
    FadeInRight(FadeInRightAnimator.class),

    FadeOut(FadeOutAnimator.class),
    FadeOutDown(FadeOutDownAnimator.class),
    FadeOutLeft(FadeOutLeftAnimator.class),
    FadeOutRight(FadeOutRightAnimator.class),
    FadeOutUp(FadeOutUpAnimator.class),

    FlipInX(FlipInXAnimator.class),
    FlipOutX(FlipOutXAnimator.class),
    FlipInY(FlipInYAnimator.class),
    FlipOutY(FlipOutYAnimator.class),
    RotateIn(RotateInAnimator.class),
    RotateInDownLeft(RotateInDownLeftAnimator.class),
    RotateInDownRight(RotateInDownRightAnimator.class),
    RotateInUpLeft(RotateInUpLeftAnimator.class),
    RotateInUpRight(RotateInUpRightAnimator.class),

    RotateOut(RotateOutAnimator.class),
    RotateOutDownLeft(RotateOutDownLeftAnimator.class),
    RotateOutDownRight(RotateOutDownRightAnimator.class),
    RotateOutUpLeft(RotateOutUpLeftAnimator.class),
    RotateOutUpRight(RotateOutUpRightAnimator.class),

    SlideInLeft(SlideInLeftAnimator.class),
    SlideInRight(SlideInRightAnimator.class),
    SlideInUp(SlideInUpAnimator.class),
    SlideInDown(SlideInDownAnimator.class),

    SlideOutLeft(SlideOutLeftAnimator.class),
    SlideOutRight(SlideOutRightAnimator.class),
    SlideOutUp(SlideOutUpAnimator.class),
    SlideOutDown(SlideOutDownAnimator.class),

    ZoomIn(ZoomInAnimator.class),
    ZoomInDown(ZoomInDownAnimator.class),
    ZoomInLeft(ZoomInLeftAnimator.class),
    ZoomInRight(ZoomInRightAnimator.class),
    ZoomInUp(ZoomInUpAnimator.class),

    ZoomOut(ZoomOutAnimator.class),
    ZoomOutDown(ZoomOutDownAnimator.class),
    ZoomOutLeft(ZoomOutLeftAnimator.class),
    ZoomOutRight(ZoomOutRightAnimator.class),
    ZoomOutUp(ZoomOutUpAnimator.class);



    private Class animatorClazz;

    private Techniques(Class clazz) {
        animatorClazz = clazz;
    }

    public BaseViewAnimator getAnimator() {
        try {
            return (BaseViewAnimator) animatorClazz.newInstance();
        } catch (Exception e) {
            throw new Error("Can not init animatorClazz instance");
        }
    }
}
