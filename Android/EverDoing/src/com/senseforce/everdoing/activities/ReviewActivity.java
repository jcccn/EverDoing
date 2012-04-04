package com.senseforce.everdoing.activities;

import android.app.Activity;
import android.content.AsyncQueryHandler;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Contacts.People;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.provider.ContactsContract.Contacts;
import android.widget.CursorTreeAdapter;
import android.widget.ExpandableListView;
import android.widget.SimpleCursorTreeAdapter;

import com.senseforce.everdoing.Constants;
import com.senseforce.everdoing.R;
import com.senseforce.framework.ui.TitleBar;

public class ReviewActivity extends Activity {
	
	private ExpandableListView mDoingListView = null;
	private static final String[] CONTACTS_PROJECTION = new String[] {
        Contacts._ID,
        Contacts.DISPLAY_NAME
    };
    private static final int GROUP_ID_COLUMN_INDEX = 0;

    private static final String[] PHONE_NUMBER_PROJECTION = new String[] {
            Phone._ID,
            Phone.NUMBER
    };

    private static final int TOKEN_GROUP = 0;
    private static final int TOKEN_CHILD = 1;

    private QueryHandler mQueryHandler;
    private CursorTreeAdapter mAdapter;
   
    
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.review_activity);
		
		Intent receivedIntent = getIntent();
		
		TitleBar titleBar = (TitleBar) findViewById(R.id.title_bar);
		String title = receivedIntent.getStringExtra(Constants.KEY_REVIEW_TITLE);
		titleBar.setTitle(title == null ? getString(R.string.bar_title_home) : title);
		
		int reviewType = receivedIntent.getIntExtra(Constants.KEY_REVIEW_TYPE, Constants.REVIEW_TYPE_THIS_WEEK);
		
		mDoingListView = (ExpandableListView)findViewById(R.id.doing_list);
		Cursor groupCursor = managedQuery(People.CONTENT_URI, new String[] {People._ID, People.NAME}, null, null, null);  
		mAdapter = new ReviewExpanableListAdapter(this, groupCursor, 
                R.layout.doing_list_item,
                new String[] { Contacts.DISPLAY_NAME}, // Name for group layouts
                new int[] { android.R.id.text1 },
                R.layout.doing_list_item,
                new String[] { Phone.NUMBER }, // Number for child layouts
                new int[] { android.R.id.text1 });
		mDoingListView.setAdapter(mAdapter);
		
		 mQueryHandler = new QueryHandler(this, mAdapter);
		 mQueryHandler.startQuery(TOKEN_GROUP, null, Contacts.CONTENT_URI, CONTACTS_PROJECTION, 
	                Contacts.HAS_PHONE_NUMBER + "=1", null, null);
				
	}
	
	
	public class ReviewExpanableListAdapter extends SimpleCursorTreeAdapter {

		public ReviewExpanableListAdapter(Context context, Cursor cursor,
				int groupLayout, String[] groupFrom, int[] groupTo,
				int childLayout, String[] childFrom, int[] childTo) {
			super(context, cursor, groupLayout, groupFrom, groupTo, childLayout, childFrom,
					childTo);
			// TODO Auto-generated constructor stub
		}

		@Override
		protected Cursor getChildrenCursor(Cursor groupCursor) {
			Uri.Builder builder = Contacts.CONTENT_URI.buildUpon();
            ContentUris.appendId(builder, groupCursor.getLong(GROUP_ID_COLUMN_INDEX));
            builder.appendEncodedPath(Contacts.Data.CONTENT_DIRECTORY);
            Uri phoneNumbersUri = builder.build();

            mQueryHandler.startQuery(TOKEN_CHILD, groupCursor.getPosition(), phoneNumbersUri, 
                    PHONE_NUMBER_PROJECTION, Phone.MIMETYPE + "=?", 
                    new String[] { Phone.CONTENT_ITEM_TYPE }, null);
            
			return null;
		}
		
		
		
	}
	
	private static final class QueryHandler extends AsyncQueryHandler {
        private CursorTreeAdapter mAdapter;

        public QueryHandler(Context context, CursorTreeAdapter adapter) {
            super(context.getContentResolver());
            this.mAdapter = adapter;
        }

        @Override
        protected void onQueryComplete(int token, Object cookie, Cursor cursor) {
            switch (token) {
            case TOKEN_GROUP:
                mAdapter.setGroupCursor(cursor);
                break;

            case TOKEN_CHILD:
                int groupPosition = (Integer) cookie;
                mAdapter.setChildrenCursor(groupPosition, cursor);
                break;
            }
        }
    }
}
