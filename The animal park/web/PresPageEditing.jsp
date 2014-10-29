<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
   <head>
   	<title>Enter a new Page</title>
   <!-- 	<link href="<c:url value="/styles.css"/>" rel="Stylesheet" type="text/css"/>    -->
   </head>

   <body>
      <!-- <jsp:include page="/header.jsp"/> -->

      <div id="editPresPage">
         <form action="editPresPage.do" method="post"> 
   	    <label>Enter Page ID</label><input type="text" name="page_id"/>
   	    <label>Enter Header1</label><input type="text" name="h1"/>
        <label>Enter Header2</label><input type="text" name="h2"/>
        <label>Enter Header3</label><input type="text" name="h3"/>
        <label>Enter Header4</label><input type="text" name="h4"/>            
		<label>Enter Page Text</label><input type="text" name="page_text"/>
     	
	    <input type="submit" value="Add New Page"/>
	 </form>		   	  
      </div>
            
      <!-- <jsp:include page="/footer.jsp"/> -->
   </body>
</html>
