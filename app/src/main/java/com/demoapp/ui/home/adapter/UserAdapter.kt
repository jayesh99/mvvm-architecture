package com.demoapp.ui.home.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.demoapp.data.pojo.UserEntity
import com.demoapp.databinding.ItemViewProgressBinding
import com.demoapp.databinding.UserRawBinding
import com.demoapp.utills.Logger
import com.sujanix.ui.base.ViewHolderBase
import com.sujanix.ui.base.ViewHolderProgress


class UserAdapter(
    val userList: MutableList<UserEntity> = ArrayList(),
    val onClick: (id: Int, userEntity: UserEntity) -> Unit
) :
    RecyclerView.Adapter<ViewHolderBase<UserEntity>>() {

    val errorMessage = MutableLiveData<String>("")


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolderBase<UserEntity> {
        if (viewType == 0)
            return ViewHolderProgress(
                ItemViewProgressBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                ), errorMessage
            )
        else {
            val binding = UserRawBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
            return EmployeeViewHolder(binding)

        }
    }

    override fun getItemCount() = if (userList.isEmpty()) 1 else userList.size


    override fun getItemViewType(position: Int): Int {
        return if (userList.isEmpty()) 0 else 1
    }

    override fun onBindViewHolder(holder: ViewHolderBase<UserEntity>, position: Int) {
        if (holder is EmployeeViewHolder) {
            holder.bind(position, userList[position])
        } else {
            holder.bind(position, UserEntity())
        }
    }


    inner class EmployeeViewHolder(val userRawBinding: UserRawBinding) :
        ViewHolderBase<UserEntity>(userRawBinding.root) {

        override fun bind(position: Int, data: UserEntity) {

            userRawBinding.userEntity = data

            userRawBinding.root.setOnClickListener {
                onClick(position, data)
            }


        }

    }


    fun updateErrorMessage(message: String) {
        errorMessage.postValue(message)
        notifyDataSetChanged()
    }



    fun update(mList: List<UserEntity>?) {
        mList?.let {
            val diffResult = DiffUtil.calculateDiff(UserDiffCallback(this.userList, it))
            this.userList.clear()
            this.userList.addAll(it)
            diffResult.dispatchUpdatesTo(this)
            Logger.log(">>>>" + userList.size)
        }
    }

    class UserDiffCallback(newPersons: List<UserEntity>, oldPersons: List<UserEntity>) :
        DiffUtil.Callback() {
        var oldUser: List<UserEntity>
        var newUser: List<UserEntity>

        override fun getOldListSize(): Int {
            return oldUser.size
        }

        override fun getNewListSize(): Int {
            return newUser.size
        }

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldUser[oldItemPosition].email.equals(newUser[newItemPosition].email,true)
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldUser[oldItemPosition].equals(newUser[newItemPosition])
        }


        override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int): Any? {
            return super.getChangePayload(oldItemPosition, newItemPosition)
        }

        init {
            this.newUser = newPersons
            this.oldUser = oldPersons
        }
    }

}