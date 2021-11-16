package hofy.notino.presentation.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.DrawableRes
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

abstract class BaseFragment<BINDING : ViewBinding> : Fragment() {
    private var _binding: BINDING? = null
    protected val binding get() = _binding!!
    protected var onIMainImageInteractionListener: IMainImageInteractionListener? = null


    abstract fun bind(inflater: LayoutInflater, container: ViewGroup?): BINDING

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is IMainImageInteractionListener) {
            onIMainImageInteractionListener = context
        }
    }

    override fun onDetach() {
        super.onDetach()
        onIMainImageInteractionListener = null
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = bind(inflater, container)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    interface IMainImageInteractionListener {
        fun onMainImageChange(@DrawableRes res: Int)
        fun onMainImageChange(url: String?)
    }
}