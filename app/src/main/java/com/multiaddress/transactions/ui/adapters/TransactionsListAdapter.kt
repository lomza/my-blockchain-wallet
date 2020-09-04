package com.multiaddress.transactions.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.multiaddress.transactions.R
import com.multiaddress.transactions.data.Transactions
import com.multiaddress.transactions.data.Tx
import com.multiaddress.transactions.utils.satoshisToBtc
import com.multiaddress.transactions.utils.toDateTime
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.listitem_transaction.*

/**
 * RecyclerView adapter for the list of transactions. Binds a transaction data with item view holder UI.
 */
class TransactionsListAdapter(
    private var transactions: Transactions
) : RecyclerView.Adapter<TransactionsListAdapter.TransactionViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        TransactionViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.listitem_transaction, parent, false)
        )

    override fun onBindViewHolder(holder: TransactionViewHolder, position: Int) = holder.bind(transactions[position])

    override fun getItemCount() = transactions.size

    class TransactionViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer {
        fun bind(transaction: Tx) {
            val context = containerView.context
            with(transaction) {
                vTransactionTimeText.text = time.toDateTime()
                vTransactionSizeText.text = containerView.context.getString(R.string.size_value, size)
                vTransactionWeightText.text = weight.toString()
                if (result < 0) {
                    vTransactionSentReceivedText.setTextColor(ContextCompat.getColor(context, R.color.purple_200))
                    vTransactionSentReceivedText.text = context.getString(R.string.sent_text)
                } else {
                    vTransactionSentReceivedText.setTextColor(ContextCompat.getColor(context, R.color.teal_200))
                    vTransactionSentReceivedText.text = context.getString(R.string.received_text)
                }
                vTransactionBtcValueText.text = context.getString(R.string.btc_value, result.satoshisToBtc().toString())
                vTransactionFeeValueText.text = context.getString(R.string.fee_value, fee.satoshisToBtc().toString())
                vTransactionHashText.text = context.getString(R.string.hash_value, hash)
                vTransactionToFromText.text = getToOrFromAddress(context, this)
            }
        }

        private fun getToOrFromAddress(context: Context, transaction: Tx): String {
            return if (transaction.result < 0) {
                val sentToAddress = transaction.out.firstOrNull { it.xpub == null }?.address
                if (sentToAddress != null) {
                    context.getString(R.string.to_address, sentToAddress)
                } else ""
            } else {
                val sentFromAddress = transaction.inputs.firstOrNull()?.previousOut?.address
                if (sentFromAddress != null) {
                    context.getString(R.string.from_address, sentFromAddress)
                } else ""
            }
        }
    }
}
