/*
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
/*
 * This code was generated by https://github.com/google/apis-client-generator/
 * (build: 2016-05-19 20:48:09 UTC)
 * on 2016-05-28 at 07:47:22 UTC 
 * Modify at your own risk.
 */

package com.spotcheck.api.spotApi;

/**
 * Service definition for SpotApi (v1).
 *
 * <p>
 * This is an API
 * </p>
 *
 * <p>
 * For more information about this service, see the
 * <a href="" target="_blank">API Documentation</a>
 * </p>
 *
 * <p>
 * This service uses {@link SpotApiRequestInitializer} to initialize global parameters via its
 * {@link Builder}.
 * </p>
 *
 * @since 1.3
 * @author Google, Inc.
 */
@SuppressWarnings("javadoc")
public class SpotApi extends com.google.api.client.googleapis.services.json.AbstractGoogleJsonClient {

  // Note: Leave this static initializer at the top of the file.
  static {
    com.google.api.client.util.Preconditions.checkState(
        com.google.api.client.googleapis.GoogleUtils.MAJOR_VERSION == 1 &&
        com.google.api.client.googleapis.GoogleUtils.MINOR_VERSION >= 15,
        "You are currently running with version %s of google-api-client. " +
        "You need at least version 1.15 of google-api-client to run version " +
        "1.22.0 of the spotApi library.", com.google.api.client.googleapis.GoogleUtils.VERSION);
  }

  /**
   * The default encoded root URL of the service. This is determined when the library is generated
   * and normally should not be changed.
   *
   * @since 1.7
   */
  public static final String DEFAULT_ROOT_URL = "https://spotcheck-3210.appspot.com/_ah/api/";

  /**
   * The default encoded service path of the service. This is determined when the library is
   * generated and normally should not be changed.
   *
   * @since 1.7
   */
  public static final String DEFAULT_SERVICE_PATH = "spotApi/v1/";

  /**
   * The default encoded base URL of the service. This is determined when the library is generated
   * and normally should not be changed.
   */
  public static final String DEFAULT_BASE_URL = DEFAULT_ROOT_URL + DEFAULT_SERVICE_PATH;

  /**
   * Constructor.
   *
   * <p>
   * Use {@link Builder} if you need to specify any of the optional parameters.
   * </p>
   *
   * @param transport HTTP transport, which should normally be:
   *        <ul>
   *        <li>Google App Engine:
   *        {@code com.google.api.client.extensions.appengine.http.UrlFetchTransport}</li>
   *        <li>Android: {@code newCompatibleTransport} from
   *        {@code com.google.api.client.extensions.android.http.AndroidHttp}</li>
   *        <li>Java: {@link com.google.api.client.googleapis.javanet.GoogleNetHttpTransport#newTrustedTransport()}
   *        </li>
   *        </ul>
   * @param jsonFactory JSON factory, which may be:
   *        <ul>
   *        <li>Jackson: {@code com.google.api.client.json.jackson2.JacksonFactory}</li>
   *        <li>Google GSON: {@code com.google.api.client.json.gson.GsonFactory}</li>
   *        <li>Android Honeycomb or higher:
   *        {@code com.google.api.client.extensions.android.json.AndroidJsonFactory}</li>
   *        </ul>
   * @param httpRequestInitializer HTTP request initializer or {@code null} for none
   * @since 1.7
   */
  public SpotApi(com.google.api.client.http.HttpTransport transport, com.google.api.client.json.JsonFactory jsonFactory,
      com.google.api.client.http.HttpRequestInitializer httpRequestInitializer) {
    this(new Builder(transport, jsonFactory, httpRequestInitializer));
  }

  /**
   * @param builder builder
   */
  SpotApi(Builder builder) {
    super(builder);
  }

  @Override
  protected void initialize(com.google.api.client.googleapis.services.AbstractGoogleClientRequest<?> httpClientRequest) throws java.io.IOException {
    super.initialize(httpClientRequest);
  }

  /**
   * Create a request for the method "insertSpot".
   *
   * This request holds the parameters needed by the spotApi server.  After setting any optional
   * parameters, call the {@link InsertSpot#execute()} method to invoke the remote operation.
   *
   * @param content the {@link com.spotcheck.api.spotApi.model.Spot}
   * @return the request
   */
  public InsertSpot insertSpot(com.spotcheck.api.spotApi.model.Spot content) throws java.io.IOException {
    InsertSpot result = new InsertSpot(content);
    initialize(result);
    return result;
  }

  public class InsertSpot extends SpotApiRequest<com.spotcheck.api.spotApi.model.Spot> {

    private static final String REST_PATH = "spot";

    /**
     * Create a request for the method "insertSpot".
     *
     * This request holds the parameters needed by the the spotApi server.  After setting any optional
     * parameters, call the {@link InsertSpot#execute()} method to invoke the remote operation. <p>
     * {@link
     * InsertSpot#initialize(com.google.api.client.googleapis.services.AbstractGoogleClientRequest)}
     * must be called to initialize this instance immediately after invoking the constructor. </p>
     *
     * @param content the {@link com.spotcheck.api.spotApi.model.Spot}
     * @since 1.13
     */
    protected InsertSpot(com.spotcheck.api.spotApi.model.Spot content) {
      super(SpotApi.this, "POST", REST_PATH, content, com.spotcheck.api.spotApi.model.Spot.class);
    }

    @Override
    public InsertSpot setAlt(java.lang.String alt) {
      return (InsertSpot) super.setAlt(alt);
    }

    @Override
    public InsertSpot setFields(java.lang.String fields) {
      return (InsertSpot) super.setFields(fields);
    }

    @Override
    public InsertSpot setKey(java.lang.String key) {
      return (InsertSpot) super.setKey(key);
    }

    @Override
    public InsertSpot setOauthToken(java.lang.String oauthToken) {
      return (InsertSpot) super.setOauthToken(oauthToken);
    }

    @Override
    public InsertSpot setPrettyPrint(java.lang.Boolean prettyPrint) {
      return (InsertSpot) super.setPrettyPrint(prettyPrint);
    }

    @Override
    public InsertSpot setQuotaUser(java.lang.String quotaUser) {
      return (InsertSpot) super.setQuotaUser(quotaUser);
    }

    @Override
    public InsertSpot setUserIp(java.lang.String userIp) {
      return (InsertSpot) super.setUserIp(userIp);
    }

    @Override
    public InsertSpot set(String parameterName, Object value) {
      return (InsertSpot) super.set(parameterName, value);
    }
  }

  /**
   * Create a request for the method "listSpot".
   *
   * This request holds the parameters needed by the spotApi server.  After setting any optional
   * parameters, call the {@link ListSpot#execute()} method to invoke the remote operation.
   *
   * @return the request
   */
  public ListSpot listSpot() throws java.io.IOException {
    ListSpot result = new ListSpot();
    initialize(result);
    return result;
  }

  public class ListSpot extends SpotApiRequest<com.spotcheck.api.spotApi.model.CollectionResponseSpot> {

    private static final String REST_PATH = "spot";

    /**
     * Create a request for the method "listSpot".
     *
     * This request holds the parameters needed by the the spotApi server.  After setting any optional
     * parameters, call the {@link ListSpot#execute()} method to invoke the remote operation. <p>
     * {@link
     * ListSpot#initialize(com.google.api.client.googleapis.services.AbstractGoogleClientRequest)}
     * must be called to initialize this instance immediately after invoking the constructor. </p>
     *
     * @since 1.13
     */
    protected ListSpot() {
      super(SpotApi.this, "GET", REST_PATH, null, com.spotcheck.api.spotApi.model.CollectionResponseSpot.class);
    }

    @Override
    public com.google.api.client.http.HttpResponse executeUsingHead() throws java.io.IOException {
      return super.executeUsingHead();
    }

    @Override
    public com.google.api.client.http.HttpRequest buildHttpRequestUsingHead() throws java.io.IOException {
      return super.buildHttpRequestUsingHead();
    }

    @Override
    public ListSpot setAlt(java.lang.String alt) {
      return (ListSpot) super.setAlt(alt);
    }

    @Override
    public ListSpot setFields(java.lang.String fields) {
      return (ListSpot) super.setFields(fields);
    }

    @Override
    public ListSpot setKey(java.lang.String key) {
      return (ListSpot) super.setKey(key);
    }

    @Override
    public ListSpot setOauthToken(java.lang.String oauthToken) {
      return (ListSpot) super.setOauthToken(oauthToken);
    }

    @Override
    public ListSpot setPrettyPrint(java.lang.Boolean prettyPrint) {
      return (ListSpot) super.setPrettyPrint(prettyPrint);
    }

    @Override
    public ListSpot setQuotaUser(java.lang.String quotaUser) {
      return (ListSpot) super.setQuotaUser(quotaUser);
    }

    @Override
    public ListSpot setUserIp(java.lang.String userIp) {
      return (ListSpot) super.setUserIp(userIp);
    }

    @com.google.api.client.util.Key
    private java.lang.Integer count;

    /**

     */
    public java.lang.Integer getCount() {
      return count;
    }

    public ListSpot setCount(java.lang.Integer count) {
      this.count = count;
      return this;
    }

    @com.google.api.client.util.Key
    private java.lang.String cursor;

    /**

     */
    public java.lang.String getCursor() {
      return cursor;
    }

    public ListSpot setCursor(java.lang.String cursor) {
      this.cursor = cursor;
      return this;
    }

    @Override
    public ListSpot set(String parameterName, Object value) {
      return (ListSpot) super.set(parameterName, value);
    }
  }

  /**
   * Create a request for the method "removeSpot".
   *
   * This request holds the parameters needed by the spotApi server.  After setting any optional
   * parameters, call the {@link RemoveSpot#execute()} method to invoke the remote operation.
   *
   * @param id
   * @return the request
   */
  public RemoveSpot removeSpot(java.lang.Long id) throws java.io.IOException {
    RemoveSpot result = new RemoveSpot(id);
    initialize(result);
    return result;
  }

  public class RemoveSpot extends SpotApiRequest<Void> {

    private static final String REST_PATH = "spot/{id}";

    /**
     * Create a request for the method "removeSpot".
     *
     * This request holds the parameters needed by the the spotApi server.  After setting any optional
     * parameters, call the {@link RemoveSpot#execute()} method to invoke the remote operation. <p>
     * {@link
     * RemoveSpot#initialize(com.google.api.client.googleapis.services.AbstractGoogleClientRequest)}
     * must be called to initialize this instance immediately after invoking the constructor. </p>
     *
     * @param id
     * @since 1.13
     */
    protected RemoveSpot(java.lang.Long id) {
      super(SpotApi.this, "DELETE", REST_PATH, null, Void.class);
      this.id = com.google.api.client.util.Preconditions.checkNotNull(id, "Required parameter id must be specified.");
    }

    @Override
    public RemoveSpot setAlt(java.lang.String alt) {
      return (RemoveSpot) super.setAlt(alt);
    }

    @Override
    public RemoveSpot setFields(java.lang.String fields) {
      return (RemoveSpot) super.setFields(fields);
    }

    @Override
    public RemoveSpot setKey(java.lang.String key) {
      return (RemoveSpot) super.setKey(key);
    }

    @Override
    public RemoveSpot setOauthToken(java.lang.String oauthToken) {
      return (RemoveSpot) super.setOauthToken(oauthToken);
    }

    @Override
    public RemoveSpot setPrettyPrint(java.lang.Boolean prettyPrint) {
      return (RemoveSpot) super.setPrettyPrint(prettyPrint);
    }

    @Override
    public RemoveSpot setQuotaUser(java.lang.String quotaUser) {
      return (RemoveSpot) super.setQuotaUser(quotaUser);
    }

    @Override
    public RemoveSpot setUserIp(java.lang.String userIp) {
      return (RemoveSpot) super.setUserIp(userIp);
    }

    @com.google.api.client.util.Key
    private java.lang.Long id;

    /**

     */
    public java.lang.Long getId() {
      return id;
    }

    public RemoveSpot setId(java.lang.Long id) {
      this.id = id;
      return this;
    }

    @Override
    public RemoveSpot set(String parameterName, Object value) {
      return (RemoveSpot) super.set(parameterName, value);
    }
  }

  /**
   * Create a request for the method "retrieveSpot".
   *
   * This request holds the parameters needed by the spotApi server.  After setting any optional
   * parameters, call the {@link RetrieveSpot#execute()} method to invoke the remote operation.
   *
   * @param id
   * @return the request
   */
  public RetrieveSpot retrieveSpot(java.lang.Long id) throws java.io.IOException {
    RetrieveSpot result = new RetrieveSpot(id);
    initialize(result);
    return result;
  }

  public class RetrieveSpot extends SpotApiRequest<com.spotcheck.api.spotApi.model.Spot> {

    private static final String REST_PATH = "retrieveSpot/{id}";

    /**
     * Create a request for the method "retrieveSpot".
     *
     * This request holds the parameters needed by the the spotApi server.  After setting any optional
     * parameters, call the {@link RetrieveSpot#execute()} method to invoke the remote operation. <p>
     * {@link
     * RetrieveSpot#initialize(com.google.api.client.googleapis.services.AbstractGoogleClientRequest)}
     * must be called to initialize this instance immediately after invoking the constructor. </p>
     *
     * @param id
     * @since 1.13
     */
    protected RetrieveSpot(java.lang.Long id) {
      super(SpotApi.this, "POST", REST_PATH, null, com.spotcheck.api.spotApi.model.Spot.class);
      this.id = com.google.api.client.util.Preconditions.checkNotNull(id, "Required parameter id must be specified.");
    }

    @Override
    public RetrieveSpot setAlt(java.lang.String alt) {
      return (RetrieveSpot) super.setAlt(alt);
    }

    @Override
    public RetrieveSpot setFields(java.lang.String fields) {
      return (RetrieveSpot) super.setFields(fields);
    }

    @Override
    public RetrieveSpot setKey(java.lang.String key) {
      return (RetrieveSpot) super.setKey(key);
    }

    @Override
    public RetrieveSpot setOauthToken(java.lang.String oauthToken) {
      return (RetrieveSpot) super.setOauthToken(oauthToken);
    }

    @Override
    public RetrieveSpot setPrettyPrint(java.lang.Boolean prettyPrint) {
      return (RetrieveSpot) super.setPrettyPrint(prettyPrint);
    }

    @Override
    public RetrieveSpot setQuotaUser(java.lang.String quotaUser) {
      return (RetrieveSpot) super.setQuotaUser(quotaUser);
    }

    @Override
    public RetrieveSpot setUserIp(java.lang.String userIp) {
      return (RetrieveSpot) super.setUserIp(userIp);
    }

    @com.google.api.client.util.Key
    private java.lang.Long id;

    /**

     */
    public java.lang.Long getId() {
      return id;
    }

    public RetrieveSpot setId(java.lang.Long id) {
      this.id = id;
      return this;
    }

    @Override
    public RetrieveSpot set(String parameterName, Object value) {
      return (RetrieveSpot) super.set(parameterName, value);
    }
  }

  /**
   * Create a request for the method "updateSpot".
   *
   * This request holds the parameters needed by the spotApi server.  After setting any optional
   * parameters, call the {@link UpdateSpot#execute()} method to invoke the remote operation.
   *
   * @param content the {@link com.spotcheck.api.spotApi.model.Spot}
   * @return the request
   */
  public UpdateSpot updateSpot(com.spotcheck.api.spotApi.model.Spot content) throws java.io.IOException {
    UpdateSpot result = new UpdateSpot(content);
    initialize(result);
    return result;
  }

  public class UpdateSpot extends SpotApiRequest<com.spotcheck.api.spotApi.model.Spot> {

    private static final String REST_PATH = "spot";

    /**
     * Create a request for the method "updateSpot".
     *
     * This request holds the parameters needed by the the spotApi server.  After setting any optional
     * parameters, call the {@link UpdateSpot#execute()} method to invoke the remote operation. <p>
     * {@link
     * UpdateSpot#initialize(com.google.api.client.googleapis.services.AbstractGoogleClientRequest)}
     * must be called to initialize this instance immediately after invoking the constructor. </p>
     *
     * @param content the {@link com.spotcheck.api.spotApi.model.Spot}
     * @since 1.13
     */
    protected UpdateSpot(com.spotcheck.api.spotApi.model.Spot content) {
      super(SpotApi.this, "PUT", REST_PATH, content, com.spotcheck.api.spotApi.model.Spot.class);
    }

    @Override
    public UpdateSpot setAlt(java.lang.String alt) {
      return (UpdateSpot) super.setAlt(alt);
    }

    @Override
    public UpdateSpot setFields(java.lang.String fields) {
      return (UpdateSpot) super.setFields(fields);
    }

    @Override
    public UpdateSpot setKey(java.lang.String key) {
      return (UpdateSpot) super.setKey(key);
    }

    @Override
    public UpdateSpot setOauthToken(java.lang.String oauthToken) {
      return (UpdateSpot) super.setOauthToken(oauthToken);
    }

    @Override
    public UpdateSpot setPrettyPrint(java.lang.Boolean prettyPrint) {
      return (UpdateSpot) super.setPrettyPrint(prettyPrint);
    }

    @Override
    public UpdateSpot setQuotaUser(java.lang.String quotaUser) {
      return (UpdateSpot) super.setQuotaUser(quotaUser);
    }

    @Override
    public UpdateSpot setUserIp(java.lang.String userIp) {
      return (UpdateSpot) super.setUserIp(userIp);
    }

    @Override
    public UpdateSpot set(String parameterName, Object value) {
      return (UpdateSpot) super.set(parameterName, value);
    }
  }

  /**
   * Builder for {@link SpotApi}.
   *
   * <p>
   * Implementation is not thread-safe.
   * </p>
   *
   * @since 1.3.0
   */
  public static final class Builder extends com.google.api.client.googleapis.services.json.AbstractGoogleJsonClient.Builder {

    /**
     * Returns an instance of a new builder.
     *
     * @param transport HTTP transport, which should normally be:
     *        <ul>
     *        <li>Google App Engine:
     *        {@code com.google.api.client.extensions.appengine.http.UrlFetchTransport}</li>
     *        <li>Android: {@code newCompatibleTransport} from
     *        {@code com.google.api.client.extensions.android.http.AndroidHttp}</li>
     *        <li>Java: {@link com.google.api.client.googleapis.javanet.GoogleNetHttpTransport#newTrustedTransport()}
     *        </li>
     *        </ul>
     * @param jsonFactory JSON factory, which may be:
     *        <ul>
     *        <li>Jackson: {@code com.google.api.client.json.jackson2.JacksonFactory}</li>
     *        <li>Google GSON: {@code com.google.api.client.json.gson.GsonFactory}</li>
     *        <li>Android Honeycomb or higher:
     *        {@code com.google.api.client.extensions.android.json.AndroidJsonFactory}</li>
     *        </ul>
     * @param httpRequestInitializer HTTP request initializer or {@code null} for none
     * @since 1.7
     */
    public Builder(com.google.api.client.http.HttpTransport transport, com.google.api.client.json.JsonFactory jsonFactory,
        com.google.api.client.http.HttpRequestInitializer httpRequestInitializer) {
      super(
          transport,
          jsonFactory,
          DEFAULT_ROOT_URL,
          DEFAULT_SERVICE_PATH,
          httpRequestInitializer,
          false);
    }

    /** Builds a new instance of {@link SpotApi}. */
    @Override
    public SpotApi build() {
      return new SpotApi(this);
    }

    @Override
    public Builder setRootUrl(String rootUrl) {
      return (Builder) super.setRootUrl(rootUrl);
    }

    @Override
    public Builder setServicePath(String servicePath) {
      return (Builder) super.setServicePath(servicePath);
    }

    @Override
    public Builder setHttpRequestInitializer(com.google.api.client.http.HttpRequestInitializer httpRequestInitializer) {
      return (Builder) super.setHttpRequestInitializer(httpRequestInitializer);
    }

    @Override
    public Builder setApplicationName(String applicationName) {
      return (Builder) super.setApplicationName(applicationName);
    }

    @Override
    public Builder setSuppressPatternChecks(boolean suppressPatternChecks) {
      return (Builder) super.setSuppressPatternChecks(suppressPatternChecks);
    }

    @Override
    public Builder setSuppressRequiredParameterChecks(boolean suppressRequiredParameterChecks) {
      return (Builder) super.setSuppressRequiredParameterChecks(suppressRequiredParameterChecks);
    }

    @Override
    public Builder setSuppressAllChecks(boolean suppressAllChecks) {
      return (Builder) super.setSuppressAllChecks(suppressAllChecks);
    }

    /**
     * Set the {@link SpotApiRequestInitializer}.
     *
     * @since 1.12
     */
    public Builder setSpotApiRequestInitializer(
        SpotApiRequestInitializer spotapiRequestInitializer) {
      return (Builder) super.setGoogleClientRequestInitializer(spotapiRequestInitializer);
    }

    @Override
    public Builder setGoogleClientRequestInitializer(
        com.google.api.client.googleapis.services.GoogleClientRequestInitializer googleClientRequestInitializer) {
      return (Builder) super.setGoogleClientRequestInitializer(googleClientRequestInitializer);
    }
  }
}
