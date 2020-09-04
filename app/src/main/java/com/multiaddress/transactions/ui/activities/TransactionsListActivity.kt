package com.multiaddress.transactions.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.annotation.StringRes
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.multiaddress.transactions.R
import com.multiaddress.transactions.data.Transactions
import com.multiaddress.transactions.data.Wallet
import com.multiaddress.transactions.ui.adapters.TransactionsListAdapter
import com.multiaddress.transactions.ui.viewmodels.TransactionsViewModel
import com.multiaddress.transactions.ui.viewmodels.TransactionsViewModel.UiEvent.ShowError
import com.multiaddress.transactions.ui.viewmodels.TransactionsViewModel.UiEvent.ShowWalletAndTransactions
import com.multiaddress.transactions.ui.viewmodels.TransactionsViewModel.UiEvent.ShowLoader
import com.multiaddress.transactions.ui.viewmodels.TransactionsViewModelFactory
import com.multiaddress.transactions.utils.satoshisToBtc
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_transactions_list.*
import javax.inject.Inject

/**
 * This is the main and only activity, which contains a wallet and transactions information.
 */
@AndroidEntryPoint
class TransactionsListActivity : AppCompatActivity() {

    private var listAdapter: TransactionsListAdapter? = null

    @Inject
    lateinit var viewModelFactory: TransactionsViewModelFactory
    private lateinit var viewModel: TransactionsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transactions_list)

        init()
        observeUiEvents()
        viewModel.fetchWalletAndTransactions()
    }

    private fun init() {
        viewModel = ViewModelProvider(this, viewModelFactory).get(TransactionsViewModel::class.java)
        vTransactionsList.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
    }

    private fun observeUiEvents() {
        viewModel.uiEvent.observe(this, Observer {
            it?.let {
                when (it) {
                    is ShowWalletAndTransactions -> {
                        vTransactionsProgress.visibility = View.GONE
                        vTransactionsErrorText.visibility = View.GONE

                        showWallet(it.wallet)
                        showTransactionList(it.transactions)
                    }
                    is ShowLoader -> showLoader()
                    is ShowError -> showError(it.messageId)
                }
            }
        })
    }

    private fun hideContent() {
        vWalletBalanceCard.visibility = View.GONE
        vTransactionsHeader.visibility = View.GONE
        vTransactionsList.visibility = View.GONE
    }

    private fun showError(@StringRes messageId: Int) {
        hideContent()
        vTransactionsProgress.visibility = View.GONE
        vTransactionsErrorText.text = getString(messageId)
        vTransactionsErrorText.visibility = View.VISIBLE
    }

    private fun showLoader() {
        hideContent()
        vTransactionsErrorText.visibility = View.GONE
        vTransactionsProgress.visibility = View.VISIBLE
    }

    private fun showWallet(wallet: Wallet) {
        vWalletBalance.text = getString(R.string.btc_value, wallet.finalBalance.satoshisToBtc())
        vWalletBalanceCard.visibility = View.VISIBLE
    }

    private fun showTransactionList(transactions: Transactions) {
        vTransactionsHeader.text = getString(R.string.transactions_header, transactions.size)
        vTransactionsHeader.visibility = View.VISIBLE
        vTransactionsList.visibility = View.VISIBLE

        listAdapter = TransactionsListAdapter(transactions)
        vTransactionsList.adapter = listAdapter
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_transactions_list, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_refresh -> {
                viewModel.fetchWalletAndTransactions()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
