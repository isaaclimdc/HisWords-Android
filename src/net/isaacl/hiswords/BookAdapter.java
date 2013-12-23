package net.isaacl.hiswords;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
//import android.widget.ImageView;

public class BookAdapter extends ArrayAdapter<Book> {
	
	Context context; 
    int resource;    
    Book objects[] = null;

	public BookAdapter(Context context, int resource, Book[] objects) {
		super(context, resource, objects);
        this.resource = resource;
        this.context = context;
        this.objects = objects;
	}
	
	@Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        BookHolder holder = null;
        
        if (row == null) {
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(resource, parent, false);
            
            holder = new BookHolder();
//            holder.imgIcon = (ImageView)row.findViewById(R.id.imageView);
            holder.textLabel = (TextView)row.findViewById(R.id.textLabel);
            
            row.setTag(holder);
        }
        else {
            holder = (BookHolder)row.getTag();
        }
        
        Book book = objects[position];
        holder.textLabel.setText(book.name);
//        holder.imgIcon.setImageResource(book.icon);
        
        return row;
    }
    
    static class BookHolder
    {
//        ImageView imgIcon;
        TextView textLabel;
    }
}
