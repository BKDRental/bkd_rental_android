package app.mimo.it.core.extensions

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.commit
import app.mimo.it.core.R

enum class FragmentAnimationType {
    SLIDE_HORIZONTAL, SLIDE_VERTICAL, FADE,
}

fun FragmentManager.openFragment(
    fragment: Fragment,
    containerId: Int,
    fragmentTag: String,
    addToBackStack: Boolean,
    isReplace: Boolean,
    animationType: FragmentAnimationType?
) {
    commit {
        when (animationType) {
            FragmentAnimationType.SLIDE_HORIZONTAL -> {
                setCustomAnimations(
                    R.anim.slide_in_hor, R.anim.fade_out,
                    R.anim.fade_in, R.anim.slide_out_hor
                )
            }
            FragmentAnimationType.SLIDE_VERTICAL -> {
                setCustomAnimations(
                    R.anim.slide_in_ver, R.anim.fade_out,
                    R.anim.fade_in, R.anim.slide_out_ver
                )
            }
            FragmentAnimationType.FADE -> {
                setCustomAnimations(
                    R.anim.fade_in, R.anim.fade_out,
                    R.anim.fade_in, R.anim.fade_out
                )
            }
        }

        if (isReplace) {
            replace(containerId, fragment, fragmentTag)
        } else {
            add(containerId, fragment, fragmentTag)
        }

        if (addToBackStack) {
            addToBackStack(fragmentTag)
        }
    }
}

fun Fragment.openFragment(
    fragment: Fragment,
    idContainer: Int,
    tagFragment: String = "",
    addToBackStack: Boolean = false,
    isReplace: Boolean = false,
    animationType: FragmentAnimationType? = null
) {
    parentFragmentManager.openFragment(
        fragment, idContainer, tagFragment,
        addToBackStack, isReplace, animationType
    )
}

fun FragmentActivity.openFragment(
    fragment: Fragment,
    idContainer: Int,
    tagFragment: String = "",
    addToBackStack: Boolean = false,
    isReplace: Boolean = false,
    animationType: FragmentAnimationType? = null
) {
    supportFragmentManager.openFragment(
        fragment, idContainer, tagFragment,
        addToBackStack, isReplace, animationType
    )
}
