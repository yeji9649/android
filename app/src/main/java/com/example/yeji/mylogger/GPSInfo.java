package com.example.yeji.mylogger;

/**
 * Created by yeji on 2016-12-01.
 */

import android.content.pm.PackageManager;
import android.location.LocationListener;
import android.os.Bundle;
import android.os.IBinder;
import android.provider.Settings;
import android.location.Location;
import android.location.LocationManager;
import android.location.OnNmeaMessageListener;
import android.content.Intent;
import android.content.Context;
import android.app.Service;
import android.support.v4.app.ActivityCompat;

public class GPSInfo extends Service implements LocationListener {
    private final Context mContext;

    boolean isGPSEnabled = false;
    boolean isNetworkEnabled = false;
    boolean isGetLocation = false;
    Location location;
    double lat = 0;//위도
    double lon = 0;//경도
    private static final long UPDATE_TIME = 10;//업데이트 되는 시간 설정
    private static final long UPDATE_DISTANCE = 100;//업데이트되는 거리설정

    public GPSInfo(Context context) {
        this.mContext = context;
        getLocation();
    }

    protected LocationManager locationManager;//위치서비스를 제공하는 서비스를 가져오는 역할을 한다

    public Location getLocation() {
        try {
            locationManager = (LocationManager) mContext
                    .getSystemService(LOCATION_SERVICE);
            isGPSEnabled = locationManager
                    .isProviderEnabled(LocationManager.GPS_PROVIDER);//gps 제공자에게gps가작동하는지아닌지를얻어온다
            isNetworkEnabled = locationManager
                    .isProviderEnabled(LocationManager.NETWORK_PROVIDER);//마찬가지로네트워크서비스제공자에서네트워크가제대로작동하는지안하는지를얻어온다

            if (!isGPSEnabled && !isNetworkEnabled) {

            } else {
                this.isGetLocation = true;
                if (isNetworkEnabled) {
                    if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                        // TODO: Consider calling
                        //    ActivityCompat#requestPermissions
                        // here to request the missing permissions, and then overriding
                        //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                        //                                          int[] grantResults)
                        // to handle the case where the user grants the permission. See the documentation
                        // for ActivityCompat#requestPermissions for more details.
                        return null;
                    }
                    locationManager.requestLocationUpdates(
                            LocationManager.NETWORK_PROVIDER, UPDATE_TIME,
                            UPDATE_DISTANCE, this);
                    if (locationManager != null) {
                        location = locationManager
                                .getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                        if (location != null) {
                            lat = location.getLatitude();
                            lon = location.getLongitude();
                        }//네크워크서비스가제대로작동해위치를받아오면각각을위도, 경도에저장한다
                    }
                }
                if (isGPSEnabled) {
                    if (location == null) {
                        locationManager.requestLocationUpdates(
                                LocationManager.GPS_PROVIDER, UPDATE_TIME,
                                UPDATE_DISTANCE, this);
                        if (locationManager != null) {
                            location = locationManager
                                    .getLastKnownLocation(LocationManager.GPS_PROVIDER);
                            if (location != null) {
                                lat = location.getLatitude();
                                lon = location.getLongitude();
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();//에러메세지의발생근원지를찾아서단계별로에러를출력한다.
        }
        return location;
    }
    /*
    public void stopUsingGPS(){
        if(locationManager != null) {
            locationManager.removeUpdates(GPSInfo, this);
        }
    }*/

    public double getLatitude() {
        if (locationManager != null) {
            lat = location.getLatitude();
        }
        return lat;
    }
    public double getLongitude() {
        if (locationManager != null) {
            lon = location.getLongitude();
        }
        return lon;
    }
    public boolean isGetLocation(){
        return this.isGetLocation;
    }
    @Override
    public void onLocationChanged(Location location) {
        //TODO Auto-generated method stub
    }


    @Override
    public void onProviderDisabled(String provider) {
        //TODO Auto-generated method stub
    }

    @Override
    public void onProviderEnabled(String provider) {
        //TODO Auto-generated method stub
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        //TODO Auto-generated method stub
    }

    public IBinder onBind(Intent arg0){
        return null;
    }

}
