package net.ivanvega.myaudiobooksclase;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import net.ivanvega.myaudiobooksclase.modelo.BookInfo;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by SERVIDOR on 15/09/2016.
 */
public class SelectorAdapter  extends BaseAdapter {
    LayoutInflater layoutInflater;
    List<BookInfo> _lst;

    public  SelectorAdapter(Context ctx, List<BookInfo> lst)
    {
        this._lst = lst;
        layoutInflater =
                (LayoutInflater) ctx.getSystemService
                        (Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return _lst.size();
    }

    @Override
    public Object getItem(int i) {
        return  _lst.get(i) ;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }



    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ImageView imageView1;
        TextView titulo;
        BookInfo   itemBook = _lst.get(i);
        View viewItem = view;
        if(view==null){
            viewItem = layoutInflater.inflate
                    (R.layout.elemento_selector,null);
        }

        titulo = (TextView)viewItem.findViewById(R.id.txtTitulo);
        titulo.setText(itemBook.getName());

        imageView1 = (ImageView)viewItem.findViewById(R.id.imageView1);
        imageView1.setImageResource(itemBook.getResourceImage());
        imageView1.setScaleType(ImageView.ScaleType.CENTER_INSIDE);

        return viewItem;
    }

}
