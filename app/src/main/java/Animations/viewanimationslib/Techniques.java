
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

package Animations.viewanimationslib;


import Animations.viewanimationslib.attention.BounceAnimator;
import Animations.viewanimationslib.attention.FlashAnimator;
import Animations.viewanimationslib.attention.PulseAnimator;
import Animations.viewanimationslib.attention.RubberBandAnimator;
import Animations.viewanimationslib.attention.ShakeAnimator;
import Animations.viewanimationslib.attention.StandUpAnimator;
import Animations.viewanimationslib.attention.SwingAnimator;
import Animations.viewanimationslib.attention.TadaAnimator;
import Animations.viewanimationslib.attention.WaveAnimator;
import Animations.viewanimationslib.attention.WobbleAnimator;
import Animations.viewanimationslib.bouncing_entrances.BounceInAnimator;
import Animations.viewanimationslib.bouncing_entrances.BounceInDownAnimator;
import Animations.viewanimationslib.bouncing_entrances.BounceInLeftAnimator;
import Animations.viewanimationslib.bouncing_entrances.BounceInRightAnimator;
import Animations.viewanimationslib.bouncing_entrances.BounceInUpAnimator;
import Animations.viewanimationslib.fading_entrances.FadeInAnimator;
import Animations.viewanimationslib.fading_entrances.FadeInDownAnimator;
import Animations.viewanimationslib.fading_entrances.FadeInLeftAnimator;
import Animations.viewanimationslib.fading_entrances.FadeInRightAnimator;
import Animations.viewanimationslib.fading_entrances.FadeInUpAnimator;
import Animations.viewanimationslib.fading_exits.FadeOutAnimator;
import Animations.viewanimationslib.fading_exits.FadeOutDownAnimator;
import Animations.viewanimationslib.fading_exits.FadeOutLeftAnimator;
import Animations.viewanimationslib.fading_exits.FadeOutRightAnimator;
import Animations.viewanimationslib.fading_exits.FadeOutUpAnimator;
import Animations.viewanimationslib.flippers.FlipInXAnimator;
import Animations.viewanimationslib.flippers.FlipInYAnimator;
import Animations.viewanimationslib.flippers.FlipOutXAnimator;
import Animations.viewanimationslib.flippers.FlipOutYAnimator;
import Animations.viewanimationslib.rotating_entrances.RotateInAnimator;
import Animations.viewanimationslib.rotating_entrances.RotateInDownLeftAnimator;
import Animations.viewanimationslib.rotating_entrances.RotateInDownRightAnimator;
import Animations.viewanimationslib.rotating_entrances.RotateInUpLeftAnimator;
import Animations.viewanimationslib.rotating_entrances.RotateInUpRightAnimator;
import Animations.viewanimationslib.rotating_exits.RotateOutAnimator;
import Animations.viewanimationslib.rotating_exits.RotateOutDownLeftAnimator;
import Animations.viewanimationslib.rotating_exits.RotateOutDownRightAnimator;
import Animations.viewanimationslib.rotating_exits.RotateOutUpLeftAnimator;
import Animations.viewanimationslib.rotating_exits.RotateOutUpRightAnimator;
import Animations.viewanimationslib.sliders.SlideInDownAnimator;
import Animations.viewanimationslib.sliders.SlideInLeftAnimator;
import Animations.viewanimationslib.sliders.SlideInRightAnimator;
import Animations.viewanimationslib.sliders.SlideInUpAnimator;
import Animations.viewanimationslib.sliders.SlideOutDownAnimator;
import Animations.viewanimationslib.sliders.SlideOutLeftAnimator;
import Animations.viewanimationslib.sliders.SlideOutRightAnimator;
import Animations.viewanimationslib.sliders.SlideOutUpAnimator;
import Animations.viewanimationslib.specials.HingeAnimator;
import Animations.viewanimationslib.specials.RollInAnimator;
import Animations.viewanimationslib.specials.RollOutAnimator;
import Animations.viewanimationslib.specials.in.DropOutAnimator;
import Animations.viewanimationslib.specials.in.LandingAnimator;
import Animations.viewanimationslib.specials.out.TakingOffAnimator;
import Animations.viewanimationslib.zooming_entrances.ZoomInAnimator;
import Animations.viewanimationslib.zooming_entrances.ZoomInDownAnimator;
import Animations.viewanimationslib.zooming_entrances.ZoomInLeftAnimator;
import Animations.viewanimationslib.zooming_entrances.ZoomInRightAnimator;
import Animations.viewanimationslib.zooming_entrances.ZoomInUpAnimator;
import Animations.viewanimationslib.zooming_exits.ZoomOutAnimator;
import Animations.viewanimationslib.zooming_exits.ZoomOutDownAnimator;
import Animations.viewanimationslib.zooming_exits.ZoomOutLeftAnimator;
import Animations.viewanimationslib.zooming_exits.ZoomOutRightAnimator;
import Animations.viewanimationslib.zooming_exits.ZoomOutUpAnimator;

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
