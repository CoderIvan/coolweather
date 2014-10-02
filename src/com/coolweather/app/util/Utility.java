package com.coolweather.app.util;

import android.text.TextUtils;

import com.coolweather.app.db.CoolWeatherDB;
import com.coolweather.app.model.City;
import com.coolweather.app.model.County;
import com.coolweather.app.model.Province;

public class Utility {
	/**
	 * 解析和处理服务器返回的省级数据
	 */
	public synchronized static boolean handleProvincesResponse(final CoolWeatherDB coolWeatherDB, String response) {
		return handleByResponse(response, new CallbackListener() {
			@Override
			public void handler(String[] array) {
				Province province = new Province();
				province.setProvinceCode(array[0]);
				province.setProvinceName(array[1]);
				coolWeatherDB.saveProvince(province);
			}
		});
	}

	/**
	 * 解析和处理服务器返回的市级数据
	 */
	public synchronized static boolean handleCitiesResponse(final CoolWeatherDB coolWeatherDB, String response, final int provinceId) {
		return handleByResponse(response, new CallbackListener() {
			@Override
			public void handler(String[] array) {
				City city = new City();
				city.setCityCode(array[0]);
				city.setCityName(array[1]);
				city.setProvinceId(provinceId);
				coolWeatherDB.saveCity(city);
			}
		});
	}

	/**
	 * 解析和处理服务器返回的县级数据
	 */
	public synchronized static boolean handleCountiesResponse(final CoolWeatherDB coolWeatherDB, String response, final int cityId) {
		return handleByResponse(response, new CallbackListener() {
			@Override
			public void handler(String[] array) {
				County county = new County();
				county.setCountyCode(array[0]);
				county.setCountyName(array[1]);
				county.setCityId(cityId);
				coolWeatherDB.saveCounty(county);
			}
		});
	}

	private static boolean handleByResponse(String response, CallbackListener listener) {
		if (!TextUtils.isEmpty(response)) {
			String[] allProvinces = response.split(",");
			if (allProvinces != null && allProvinces.length > 0) {
				for (String p : allProvinces) {
					String[] array = p.split("\\|");
					listener.handler(array);
				}
				return true;
			}
		}
		return false;
	}

	interface CallbackListener {
		void handler(String[] array);
	}
}
