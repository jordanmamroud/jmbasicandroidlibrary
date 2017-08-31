
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

package view.Animations.viewanimationslib;


import view.Animations.viewanimationslib.attention.BounceAnimator;
import view.Animations.viewanimationslib.attention.FlashAnimator;
import view.Animations.viewanimationslib.attention.PulseAnimator;
import view.Animations.viewanimationslib.attention.RubberBandAnimator;
import view.Animations.viewanimationslib.attention.ShakeAnimator;
import view.Animations.viewanimationslib.attention.StandUpAnimator;
import view.Animations.viewanimationslib.attention.SwingAnimator;
import view.Animations.viewanimationslib.attention.TadaAnimator;
import view.Animations.viewanimationslib.attention.WaveAnimator;
import view.Animations.viewanimationslib.attention.WobbleAnimator;
import view.Animations.viewanimationslib.bouncing_entrances.BounceInAnimator;
import view.Animations.viewanimationslib.bouncing_entrances.BounceInDownAnimator;
import view.Animations.viewanimationslib.bouncing_entrances.BounceInLeftAnimator;
import view.Animations.viewanimationslib.bouncing_entrances.BounceInRightAnimator;
import view.Animations.viewanimationslib.bouncing_entrances.BounceInUpAnimator;
import view.Animations.viewanimationslib.fading_entrances.FadeInAnimator;
import view.Animations.viewanimationslib.fading_entrances.FadeInDownAnimator;
import view.Animations.viewanimationslib.fading_entrances.FadeInLeftAnimator;
import view.Animations.viewanimationslib.fading_entrances.FadeInRightAnimator;
import view.Animations.viewanimationslib.fading_entrances.FadeInUpAnimator;
import view.Animations.viewanimationslib.fading_exits.FadeOutAnimator;
import view.Animations.viewanimationslib.fading_exits.FadeOutDownAnimator;
import view.Animations.viewanimationslib.fading_exits.FadeOutLeftAnimator;
import view.Animations.viewanimationslib.fading_exits.FadeOutRightAnimator;
import view.Animations.viewanimationslib.fading_exits.FadeOutUpAnimator;
import view.Animations.viewanimationslib.flippers.FlipInXAnimator;
import view.Animations.viewanimationslib.flippers.FlipInYAnimator;
import view.Animations.viewanimationslib.flippers.FlipOutXAnimator;
import view.Animations.viewanimationslib.flippers.FlipOutYAnimator;
import view.Animations.viewanimationslib.rotating_entrances.RotateInAnimator;
import view.Animations.viewanimationslib.rotating_entrances.RotateInDownLeftAnimator;
import view.Animations.viewanimationslib.rotating_entrances.RotateInDownRightAnimator;
import view.Animations.viewanimationslib.rotating_entrances.RotateInUpLeftAnimator;
import view.Animations.viewanimationslib.rotating_entrances.RotateInUpRightAnimator;
import view.Animations.viewanimationslib.rotating_exits.RotateOutAnimator;
import view.Animations.viewanimationslib.rotating_exits.RotateOutDownLeftAnimator;
import view.Animations.viewanimationslib.rotating_exits.RotateOutDownRightAnimator;
import view.Animations.viewanimationslib.rotating_exits.RotateOutUpLeftAnimator;
import view.Animations.viewanimationslib.rotating_exits.RotateOutUpRightAnimator;
import view.Animations.viewanimationslib.sliders.SlideInDownAnimator;
import view.Animations.viewanimationslib.sliders.SlideInLeftAnimator;
import view.Animations.viewanimationslib.sliders.SlideInRightAnimator;
import view.Animations.viewanimationslib.sliders.SlideInUpAnimator;
import view.Animations.viewanimationslib.sliders.SlideOutDownAnimator;
import view.Animations.viewanimationslib.sliders.SlideOutLeftAnimator;
import view.Animations.viewanimationslib.sliders.SlideOutRightAnimator;
import view.Animations.viewanimationslib.sliders.SlideOutUpAnimator;
import view.Animations.viewanimationslib.specials.HingeAnimator;
import view.Animations.viewanimationslib.specials.RollInAnimator;
import view.Animations.viewanimationslib.specials.RollOutAnimator;
import view.Animations.viewanimationslib.specials.in.DropOutAnimator;
import view.Animations.viewanimationslib.specials.in.LandingAnimator;
import view.Animations.viewanimationslib.specials.out.TakingOffAnimator;
import view.Animations.viewanimationslib.zooming_entrances.ZoomInAnimator;
import view.Animations.viewanimationslib.zooming_entrances.ZoomInDownAnimator;
import view.Animations.viewanimationslib.zooming_entrances.ZoomInLeftAnimator;
import view.Animations.viewanimationslib.zooming_entrances.ZoomInRightAnimator;
import view.Animations.viewanimationslib.zooming_entrances.ZoomInUpAnimator;
import view.Animations.viewanimationslib.zooming_exits.ZoomOutAnimator;
import view.Animations.viewanimationslib.zooming_exits.ZoomOutDownAnimator;
import view.Animations.viewanimationslib.zooming_exits.ZoomOutLeftAnimator;
import view.Animations.viewanimationslib.zooming_exits.ZoomOutRightAnimator;
import view.Animations.viewanimationslib.zooming_exits.ZoomOutUpAnimator;

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
