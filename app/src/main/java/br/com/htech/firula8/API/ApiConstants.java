/*
 * Copyright 2017 Jiaheng Ge
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package br.com.htech.firula8.API;

public final class ApiConstants {

    private ApiConstants() {
        throw new AssertionError("No construction for constant class");
    }

    // general constants of Dribbble API
    public static final String DRIBBBLE_V1_BASE_URL = "https://api.dribbble.com";
    public static final String DRIBBBLE_AUTHORIZE_URL = "https://dribbble.com/oauth/authorize";
    public static final String DRIBBBLE_GET_ACCESS_TOKEN_URL = "https://dribbble.com/oauth/token";

    // for both flavor open and play
    public static final String DRIBBBLE_AUTHORIZE_CALLBACK_URI = "x-firula8-oauth-dribbble://callback";
    public static final String DRIBBBLE_AUTHORIZE_CALLBACK_URI_SCHEMA = "x-firula8-oauth-dribbble";
    public static final String DRIBBBLE_AUTHORIZE_CALLBACK_URI_HOST = "callback";
    public static final String DRIBBBLE_AUTHORIZE_SCOPE = "public write comment upload";

    public static final int PER_PAGE = 20;

    public static final String APPLICATION_ID = "br.com.htech.firula8";
    public static final String DRIBBBLE_CLIENT_ACCESS_TOKEN = "9f96a13d91e5733d3840ebad5df5e25c8582b62fbe790a0bb8b34e127365d721";
    public static final String DRIBBBLE_CLIENT_ID = "b2b9dc49ff651a0f0ae9ec2c84e34ccb18d04fa9eef4fa46d8feefc8f514653a";
    public static final String DRIBBBLE_CLIENT_SECRET = "07c734e9e0c944121ad3b8cf75633e847225fd79149a86d8e38e5eb704c6af0c";
}
