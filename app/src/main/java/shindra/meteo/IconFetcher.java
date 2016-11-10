package shindra.meteo;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Guillaume on 10/11/2016.
 */

public class IconFetcher implements Runnable{

    private URL url2Connect;

    public IconFetcher(URL anURl){
        url2Connect = anURl;
    }



    @Override
    public void run() {

        try {



        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
