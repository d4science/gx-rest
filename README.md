gxRest
----
The gCube eXtensions to the Rest Protocol (gxRest) is a set of Java libraries designed to provide convenient round-trip interaction between a Restful web application (also known as "service") and its clients. 

gxRest has the flexibility for different degrees of exploitation:
* it can be entirely adopted both at client and service side with full benefit of its conventions;
* it can be used to send REST requests based only on plain HTTP;
* it can be used only to return HTTP code/messagess from the service;
* it can be used only to throw Exceptions from the service to the client.
    
## Examples of use

Sending a request:
```java
GXHTTPRequest request = GXHTTPRequest.newRequest("http://host:port/service/").from("GXRequestTest");
 
//prepare some parameters
String context ="...";
Map<String,String> queryParams = new WeakHashMap<>();
queryParams.put("param name", "param value");

GXInboundResponse response = request.path("context")
	  .queryParams(queryParams).withBody(context).post();

```
   
Returning a success response:   
```java
 @POST
  public Response create(...) {
 
    //Resource successfully created 
 
    URI location = ... //URI of the new resource
    return GXOutboundSuccessResponse.newCREATEResponse(location)
              .withMessage("Context successfully created.")
	      .ofType(MediaType.APPLICATION_JSON)
              .build();
  }
``` 

Throwing an exception:   
```java
@POST
  public Response create(...) {
 
    //Oh no, something failed here
    GXOutboundErrorResponse.throwException(new RMContextAlreadyExistException("Context already exists."));
    
  }   
``` 


Parsing a response
```java
// invoke the create method and get a response.
Response create = target("context").queryParam(...).request()
		.post(Entity.entity(ISMapper.marshal(newContext), MediaType.APPLICATION_JSON + ";charset=UTF-8"));
 
//wrap the response
GXInboundResponse response = new GXInboundResponse(create);
if (response.hasException()) 
    throw response.getException();
//assuming a created (200) code was expected 
if (response.hasCREATEDCode()) {
		System.out.println("Resource successfully created!");
}
//access content as string
String value = response.getStreamedContentAsString();

//unmasharll content as json
Context returnedContext = response.tryConvertStreamedContentFromJson(Context.class);
``` 
    
## Modules
* [gxHTTP](https://wiki.gcube-system.org/gcube/GxRest/GxHTTP)
* [gxJRS](https://wiki.gcube-system.org/gcube/GxRest/GxJRS)
    
## Deployment
    
Notes about how to deploy this on an infrastructure or link to  wiki.

## Documentation
See gxRest on [Wiki](https://wiki.gcube-system.org/gcube/GxRest).

## Built With

* [JAX-RS](https://github.com/eclipse-ee4j/jaxrs-api) - Java™ API for RESTful Web Services
* [Jersey](https://jersey.github.io/) - JAX-RS runtime
* [Maven](https://maven.apache.org/) - Dependency Management

## License
TBP