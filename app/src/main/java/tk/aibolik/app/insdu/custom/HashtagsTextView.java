package tk.aibolik.app.insdu.custom;

import android.content.Context;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import tk.aibolik.app.insdu.R;

/**
 * Created by Aibol Kussain on Jun 21, 2016.
 * Working on "inSDUv2". Smart Digital Solutions
 * You can contact me at: aibolikdev@gmail.com
 */
public class HashtagsTextView extends TextView {

    private static final String TAG = HashtagsTextView.class.getSimpleName();

    private HastTagClickListener mClickListener;

    ClickableSpan hashtag = new ClickableSpan() {
        @Override
        public void onClick(View widget) {
            mClickListener.onHashTagClick(this.toString());
        }

        @Override
        public void updateDrawState(TextPaint ds) {
            super.updateDrawState(ds);
            ds.setColor(getResources().getColor(R.color.colorAccent));
        }
    };

    public HashtagsTextView(Context context) {
        this(context, null, 0);
    }

    public HashtagsTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public HashtagsTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void setText(CharSequence text, BufferType type) {
        SpannableString hashtagText = new SpannableString(text);
        String tags = String.valueOf(hashtagText);

        Log.d(TAG, "flag1: ");

        int index = -1;
        int offset = -1;
        Log.d(TAG, "setText: " + tags);
        while((index = tags.indexOf("#")) != -1) {
            int nextSpace = tags.indexOf(" ");
            int nextNewline = tags.indexOf("\n");
            offset = index;
//            Log.d(TAG, "tillspace-" + tags.substring(index, nextSpace));
//            Log.d(TAG, "tilllineend-" + tags.substring(index, nextNewline));
            hashtagText.setSpan(
                    hashtag,
                    offset + index,
                    offset + Math.min(nextSpace == -1 ? Integer.MAX_VALUE : nextSpace, nextNewline == -1 ? Integer.MAX_VALUE : nextNewline) + 1,
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
            );
            tags = tags.substring(index + 1);
            Log.d(TAG, "in while");
        }
        super.setText(hashtagText, type);
    }

    public void setHashtagClickListener(HastTagClickListener listener) {
        mClickListener = listener;
    }

    public interface HastTagClickListener {
        void onHashTagClick(String hashtag);
    }
}
