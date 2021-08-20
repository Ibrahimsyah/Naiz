package com.bccowo.naiz.presentation.home.bookmark

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bccowo.naiz.R
import com.bccowo.naiz.databinding.DialogDeleteBookmarksBinding
import com.bccowo.naiz.databinding.FragmentBookmarkBinding
import com.bccowo.naiz.core.ui.CandiListAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class BookmarkFragment : Fragment() {

    private val bookmarkViewModel: BookmarkViewModel by viewModel()
    private var _binding: FragmentBookmarkBinding? = null
    private val binding get() = _binding!!
    private var isDataEmpty = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBookmarkBinding.inflate(inflater)
        val navController = findNavController()
        with(binding.toolbar) {
            NavigationUI.setupWithNavController(this, navController)
            setHasOptionsMenu(true)
            (activity as AppCompatActivity).setSupportActionBar(this)
            (activity as AppCompatActivity).supportActionBar?.setDisplayShowTitleEnabled(false)
            setNavigationOnClickListener { view ->
                view.findNavController().navigateUp()
            }
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val candiListAdapter = CandiListAdapter {}
        with(binding.rvBookmark) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = candiListAdapter
        }

        val callback = ItemTouchHelper(TouchHelperCallback())
        callback.attachToRecyclerView(binding.rvBookmark)

        bookmarkViewModel.getBookmark().observe(viewLifecycleOwner, {
            isDataEmpty = it.isEmpty()
            candiListAdapter.setData(it)
        })
    }

    inner class TouchHelperCallback : ItemTouchHelper.Callback() {
        override fun getMovementFlags(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder
        ): Int = makeMovementFlags(0, ItemTouchHelper.RIGHT)

        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ): Boolean = false

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            val candi = (viewHolder as CandiListAdapter.CandiListViewHolder).getCandi()
            bookmarkViewModel.removeCandiFromBookmark(candi)
            Toast.makeText(
                context,
                getString(R.string.bookmark_deleted).format(candi.name),
                Toast.LENGTH_SHORT
            ).show()
        }

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.bookmark_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.delete_bookmarks -> {
                if (!isDataEmpty) actionDeleteAllBookmark()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun actionDeleteAllBookmark() {
        val builder = AlertDialog.Builder(context as Context)
        val view = DialogDeleteBookmarksBinding.inflate(layoutInflater)
        builder.setView(view.root)
        val alertDialog = builder.create()
        alertDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        alertDialog.show()
        view.btnCancel.setOnClickListener {
            alertDialog.cancel()
        }
        view.btnConfirmDelete.setOnClickListener {
            bookmarkViewModel.removeAllBookmark()
            Toast.makeText(context, getString(R.string.all_bookmark_deleted), Toast.LENGTH_SHORT)
                .show()
            alertDialog.cancel()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}