package mobiskif.healthy;

import android.content.Context;
import android.content.Intent;
import android.speech.tts.TextToSpeech;

/**
 * Created by mobis on 16.07.2017.
 */

public class GoogleSpeech extends TextToSpeech {

    public GoogleSpeech(Context context, OnInitListener listener) {
        super(context, listener);
        //selectFile("*/*"); break;
        if ( TextToSpeech.Engine.CHECK_VOICE_DATA_PASS!=1 ) {
            Intent installIntent = new Intent();
            installIntent.setAction(TextToSpeech.Engine.ACTION_INSTALL_TTS_DATA);
            context.startActivity(installIntent);
        }
    }

    private void selectFile(String type) {
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType(type);
        //startActivityForResult(intent, 7);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==666) {
            L.d(data.getExtras().getString("query"),this);
        }
        /*
        else if (requestCode == 7 && data != null) {
            //Uri uri = data.getData();
            try {
                FileDescriptor fd = this
                        .getContentResolver()
                        .openFileDescriptor(data.getData(), "r")
                        .getFileDescriptor();
                FileInputStream fis = new FileInputStream(fd);
                StringBuffer fileContent = new StringBuffer("");
                byte[] buffer = new byte[1024];
                int n;
                while ((n = fis.read(buffer)) != -1) fileContent.append(new String(buffer, 0, n));
                fis.close();

                speechString = fileContent.toString();
                L.d(speechString, this);
                Intent checkIntent = new Intent();
                checkIntent.setAction(TextToSpeech.Engine.ACTION_CHECK_TTS_DATA);
                startActivity(checkIntent);

                if (TextToSpeech.Engine.CHECK_VOICE_DATA_PASS ==1 ) {

                    speechEngine = new TextToSpeech(this, this);
                } else {
                    Intent installIntent = new Intent();
                    installIntent.setAction(TextToSpeech.Engine.ACTION_INSTALL_TTS_DATA);
                    startActivity(installIntent);
                }
            }
            catch (Exception e) {
                L.d("Не могу говорить: "+e.toString(),this);
            }
        }
        */
    }
}
