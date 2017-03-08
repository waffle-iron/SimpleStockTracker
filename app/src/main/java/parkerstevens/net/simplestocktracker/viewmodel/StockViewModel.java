package parkerstevens.net.simplestocktracker.viewmodel;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.graphics.Color;

import java.math.BigDecimal;

import parkerstevens.net.simplestocktracker.data.StocksHelper;
import parkerstevens.net.simplestocktracker.model.Stock;
import parkerstevens.net.simplestocktracker.model.Transaction;

/**
 * Created by pstev on 3/3/2017.
 */

public class StockViewModel extends BaseObservable {
    private StocksHelper mStocksHelper;
    private Stock mStock;
    private Transaction mTrans;

    public StockViewModel(Context context) {
        mStocksHelper = StocksHelper.get(context);
    }

    public void setStock(Stock stock){
        mStock = stock;
        notifyChange();
    }

    public void setTrans(Transaction trans) {
        mTrans = trans;
    }

    private BigDecimal calcProfits(){

        BigDecimal purchasePrice = mTrans.getPrice();
        BigDecimal quantity = new BigDecimal(mTrans.getQuantity());
        BigDecimal fees = mTrans.getFees();
        BigDecimal price = mStock.getLastPrice();

        return ((purchasePrice
                .multiply(quantity))
                .add(fees))
                .subtract(
                        (price.multiply(quantity))
                );


    }

    @Bindable
    public String getName(){ return mStock.getName();}

    @Bindable
    public String getSymbol(){return  mStock.getSymbol();}

    @Bindable
    public String getLastPrice(){return mStock.getLastPrice() + "";}

    @Bindable
    public String getChangePercent(){return Math.floor(mStock.getChangePercent() * 100)/100 + "%";}

    @Bindable
    public double getChangePerInt() {return mStock.getChangePercent();}

    @Bindable
    public String getChangeDollars() {return "$"+ Math.floor(mStock.getChange() * 100)/100;}

    @Bindable
    public String getPurchasePrice(){return mTrans.getPrice() + "";}

    @Bindable
    public String getSharesAmount(){return mTrans.getQuantity() + "";}

    @Bindable
    public String getProfits() {return calcProfits() + ""; }

    @Bindable
    public int getGreen(){return Color.GREEN;}

    @Bindable
    public int getRed(){return Color.RED;}

}
