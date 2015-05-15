/*
 * Copyright 2009-2015 the CodeLibs Project and the Others.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */

package jp.sf.fess.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import jp.sf.fess.entity.LoginInfo;

import org.codelibs.sastruts.core.SSCConstants;
import org.codelibs.sastruts.core.entity.UserInfo;
import org.codelibs.sastruts.core.filter.AuthFilter;

public class AdminAuthFilter extends AuthFilter {
    @Override
    protected UserInfo getUserInfo(final HttpServletRequest req) {
        final HttpSession session = req.getSession(false);
        if (session == null) {
            return null;
        }
        final Object obj = session.getAttribute(SSCConstants.USER_INFO);
        if (obj instanceof LoginInfo) {
            final LoginInfo loginInfo = (LoginInfo) obj;
            if (loginInfo.isAdministrator()) {
                return loginInfo;
            }
        }
        return null;
    }
}