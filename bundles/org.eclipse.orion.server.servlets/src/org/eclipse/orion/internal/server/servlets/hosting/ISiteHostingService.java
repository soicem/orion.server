/*******************************************************************************
 * Copyright (c) 2011 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.orion.internal.server.servlets.hosting;

import org.eclipse.orion.internal.server.servlets.site.SiteConfiguration;
import org.eclipse.orion.internal.server.servlets.workspace.WebUser;

/**
 * API for a service that can launch hosted sites from a site configuration, and query hosted 
 * sites that were launched.<p>
 * 
 * This API allows at most one hosted site to be running concurrently for any given 
 * <i>WebUser + SiteConfiguration</i> pair.<p>
 * 
 * TODO: This should be replaced with a real protocol for managing hosted sites and support
 * remote hosting.
 */
public interface ISiteHostingService {

	/**
	 * Starts a site configuration for the given user.
	 * @param siteConfig
	 * @param user
	 * @param editServer
	 * @throws SiteHostingException If starting failed.
	 */
	public void start(SiteConfiguration siteConfig, WebUser user, String editServer) throws SiteHostingException;

	/**
	 * Stops the user's hosted site which was launched from the site configuration.
	 * @param siteConfig
	 * @param user
	 * @throws SiteHostingException If stopping failed.
	 */
	public void stop(SiteConfiguration siteConfig, WebUser user) throws SiteHostingException;

	/**
	 * @param host A host name.
	 * @param user 
	 * @return The hosted site launched by <code>user</code> from <code>siteConfig</code>, or
	 * <code>null</code> if there is no such hosted site.
	 */
	public IHostedSite get(SiteConfiguration siteConfig, WebUser user);

	/**
	 * @param host A host name.
	 * @return <code>true</code> if there is a hosted site running at <code>host</code>.
	 */
	public boolean isHosted(String host);

	/**
	 * @param host A host name.
	 * @return <code>true</code> if <code>host</code> is a virtual host name that a site could
	 * be running on.
	 */
	public boolean matchesVirtualHost(String host);

}
