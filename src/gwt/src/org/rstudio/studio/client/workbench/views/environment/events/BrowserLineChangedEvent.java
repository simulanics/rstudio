/*
 * BrowserLineChangedEvent.java
 *
 * Copyright (C) 2009-12 by RStudio, Inc.
 *
 * Unless you have received this program directly from RStudio pursuant
 * to the terms of a commercial license agreement with RStudio, then
 * this program is licensed to you under the terms of version 3 of the
 * GNU Affero General Public License. This program is distributed WITHOUT
 * ANY EXPRESS OR IMPLIED WARRANTY, INCLUDING THOSE OF NON-INFRINGEMENT,
 * MERCHANTABILITY OR FITNESS FOR A PARTICULAR PURPOSE. Please refer to the
 * AGPL (http://www.gnu.org/licenses/agpl-3.0.txt) for more details.
 *
 */
package org.rstudio.studio.client.workbench.views.environment.events;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;

public class BrowserLineChangedEvent
        extends GwtEvent<BrowserLineChangedEvent.Handler>
{
   // at some point, this may also include the point inside the line where the
   // expression being evaluated exists
   public static class LineData extends JavaScriptObject
   {
      protected LineData()
      {
      }

      public final native int getLineNumber() /*-{
          return this.line_number;
      }-*/;

   }

   public interface Handler extends EventHandler
   {
      void onBrowserLineChanged(BrowserLineChangedEvent event);
   }

   public BrowserLineChangedEvent(LineData data)
   {
      lineData_ = data;
   }

   public int getLineNumber()
   {
      return lineData_.getLineNumber();
   }

   @Override
   public Type<Handler> getAssociatedType()
   {
      return TYPE;
   }

   @Override
   protected void dispatch(Handler handler)
   {
      handler.onBrowserLineChanged(this);
   }

   public static final Type<Handler> TYPE = new Type<Handler>();
   private final LineData lineData_;
}