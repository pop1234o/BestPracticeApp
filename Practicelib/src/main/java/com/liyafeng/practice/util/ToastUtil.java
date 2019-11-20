
import android.support.annotation.IdRes;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class ToastUtil {


    private static void showToast(String content, int resId) {
        BaseApplication context = BaseApplication.getIns();
        Toast toast = new Toast(context);
        View view = LayoutInflater.from(context).inflate(R.layout.common_toast, null);
        ImageView ivToastIcon = view.findViewById(R.id.ivToastIcon);
        TextView tvToastText = view.findViewById(R.id.tvToastText);

        ivToastIcon.setImageResource(resId);
        tvToastText.setText(content);
        toast.setView(view);
        toast.setGravity(Gravity.CENTER, 0, 0);

        toast.setDuration(Toast.LENGTH_SHORT);
        toast.show();
    }

}
