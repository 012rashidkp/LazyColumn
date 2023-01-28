package com.example.lazycolumn.ViewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lazycolumn.FormValidation.LoginFormEvent
import com.example.lazycolumn.FormValidation.ProductFormEvent
import com.example.lazycolumn.FormValidation.ProductValidationFormState
import com.example.lazycolumn.Domain.Use_Cases.ValidateProductDesc
import com.example.lazycolumn.Domain.Use_Cases.ValidateProductName
import com.example.lazycolumn.Domain.Use_Cases.ValidateProductPrice
import com.example.lazycolumn.Domain.Use_Cases.ValidateProductQty
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class ProductValidationViewModel(private val validateProductName: ValidateProductName = ValidateProductName(), private val validateProductDesc: ValidateProductDesc = ValidateProductDesc(), private val validateProductPrice: ValidateProductPrice = ValidateProductPrice(), private val validateProductQty: ValidateProductQty = ValidateProductQty()):ViewModel() {
    var state by mutableStateOf(ProductValidationFormState())
    private val validationeventchannel= Channel<ValidationEvent>()
    val validationEvents=validationeventchannel.receiveAsFlow()
    fun onEvent(event: ProductFormEvent){
        when(event){
            is ProductFormEvent.ProductNameChanged->{
                state=state.copy(productName = event.productName)
            }
            is ProductFormEvent.productdescChanged->{
                state=state.copy(productdesc = event.productdesc)
            }
            is ProductFormEvent.productpricechanged->{
                state=state.copy(productprice = event.productprice)
            }
            is ProductFormEvent.productqtychanged->{
                state=state.copy(productqty = event.productqty)
            }

            is ProductFormEvent.Saveproducts->{
                SavingProductValidated()
            }
        }
    }
    private fun SavingProductValidated() {
        val productNameresult=validateProductName.execute(state.productName)
        val productDescresult=validateProductDesc.execute(state.productdesc)
        val productPriceresult=validateProductPrice.execute(state.productprice)
        val productQtyresult=validateProductQty.execute(state.productqty)
        val haserror= listOf(productNameresult,productDescresult,productPriceresult,productQtyresult).any { !it.successfull }

        if (haserror){
            state=state.copy(
                productNameError = productNameresult.ErrorMessage,
                productdescError = productDescresult.ErrorMessage,
                productpriceError =productPriceresult.ErrorMessage,
                productqtyError = productQtyresult.ErrorMessage

            )
            return
        }
        viewModelScope.launch {
            validationeventchannel.send(ProductValidationViewModel.ValidationEvent.success)
        }
    }

    sealed class ValidationEvent{
        object success: ValidationEvent()
    }
}