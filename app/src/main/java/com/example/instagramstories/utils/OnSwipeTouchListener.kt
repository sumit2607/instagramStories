package com.example.instagramstories.utils

//open class OnSwipeTouchListener(ctx: Context?) : View.OnTouchListener {
//    private val gestureDetector: GestureDetector = GestureDetector(ctx, GestureListener())
//
//    override fun onTouch(v: View?, event: MotionEvent?): Boolean {
//        return gestureDetector.onTouchEvent(event)
//    }
//
//    private inner class GestureListener : GestureDetector.SimpleOnGestureListener() {
//        private val SWIPE_THRESHOLD = 100
//        private val SWIPE_VELOCITY_THRESHOLD = 100
//
//        override fun onDown(e: MotionEvent): Boolean {
//            return true
//        }
//
//        override fun onFling(e1: MotionEvent, e2: MotionEvent, velocityX: Float, velocityY: Float): Boolean {
//            val diffX = e2.x - e1.x
//            val diffY = e2.y - e1.y
//            return if (Math.abs(diffX) > Math.abs(diffY)) {
//                if (Math.abs(diffX) > SWIPE_THRESHOLD && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
//                    if (diffX > 0) {
//                        onSwipeRight()
//                    } else {
//                        onSwipeLeft()
//                    }
//                    true
//                } else {
//                    false
//                }
//            } else {
//                false
//            }
//        }
//    }
//
//    open fun onSwipeRight() {}
//    open fun onSwipeLeft() {}
//}
