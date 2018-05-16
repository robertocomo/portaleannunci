<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="bean.AnnuncioBean"%>
<%@ page import="model.CategoriaAnnuncio"%>     
<jsp:useBean id="searchBean" scope="request" class="bean.SearchBean" />
<jsp:useBean id="loginBean" scope="session" class="bean.LoginBean" />
<jsp:setProperty name="searchBean" property="*" />
    
<%
	if (request.getParameter("search") == null)
		response.sendRedirect("./index.jsp");

%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <title>Risultati Ricerca</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  <link href="./css/jumbotron-narrow.css" rel="stylesheet">
<style>


	.noHover:hover {
  		background-color: #e1a92f !important;
}

  /* mixin for multiline */
    .block-with-text {
      overflow: hidden;
      position: relative;
      line-height: 1.2em;
      max-height: 2.4em;
      text-align: justify;
      margin-right: -1em;
      padding-right: 1em;
    }

    .block-with-text:before {
        content: '...';
        position: absolute;
        right: 0;
        bottom: 0;
    }

    .block-with-text:after {
        content: '';
        position: absolute;
        right: 0;
        width: 1em;
        height: 1em;
        margin-top: 0.2em;
        background: white;
    }




    /* Set height of the grid so .sidenav can be 100% (adjust if needed) */
    .row.content {height: 1500px}
    
    /* Set gray background color and 100% height */
    .sidenav {
      background-color: #f1f1f1;
      height: 100%;
    }
    
    /* Set black background color, white text and some padding */
    footer {
      padding: 15px;
      padding-top:40px;
      list-style: none;
    }
    
		
	footer li {
	  border-left: solid 1px;
	  display: inline-block;
	  line-height: 1em;
	  margin-left: 1em;
	  padding: 0 0 0 1em;
	}
	
	footer li:first-child {
	  border-left: 0;
	  margin-left: 0;
	  padding: 0;
	}
    
    /* On small screens, set height to 'auto' for sidenav and grid */
    @media screen and (max-width: 767px) {
      .sidenav {
        height: auto;
        padding: 15px;
      }
      .row.content {height: auto;} 
    }
  </style>
</head>
<body style="
    font-family: -apple-system,system-ui,BlinkMacSystemFont,&quot;Segoe UI&quot;,Roboto,&quot;Helvetica Neue&quot;,Arial,sans-serif;
">
    <div class="container-fluid">
<jsp:include page="header.jsp"/></div>


<div class="container-fluid">
<div class="row content" style="
    background-color: #f1f1f1;
    height: 100%;
    margin-left: 3.5em;
    margin-right: 3.5em;
    padding-top: 0.5em;
">
    <div class="col-sm-2 sidenav">
      <br><h4 style="text-align: center;">Perfeziona la ricerca</h4><br>
      <%
      
      if(loginBean.userIsNotLogged())
      {
    	%>
    	<div class="alert alert-warning" role="alert">
        	  <a href="Login.jsp" class="alert-link">Accedi</a> per utilizzare la funzionalità di ricerca avanzata.
       	</div>
       <%
       
       }%>	
      
<form class="form-horizontal" action="RisultatiRicerca.jsp" id="myform" name="myform" method="POST">
    

      <ul class="nav nav-pills nav-stacked">
        <li class="active">
        <input class="form-control input-lg" name="title" type="text" id="title" placeholder="<%
        out.print(searchBean.getTitle());%>" value="<%out.print(searchBean.getTitle());%>" <%
      out.print(loginBean.userIsNotLogged() ? "disabled" : "");%>></li>
        <br><li> <label for="categoria" style="text-align: left !important;">Categoria</label>
  <select class="form-control" id="category" name="category" <%
      out.print(loginBean.userIsNotLogged() ? "disabled" : "");%>>
      
      
      
      	<%
     		 	String category = searchBean.getCategory();
		 		boolean categoryNotNull = category != null;
      		
		%>
		<option value="" <%
    						if(categoryNotNull)
    							out.print(category.equals("") ? "selected" : "");
    					%>> Qualsiasi</option>
		
		<%
		 	for (CategoriaAnnuncio type : CategoriaAnnuncio.values())
    	 	{
    		%>	 
    			<option value="<%out.print(type.name()); %>" <%
    			
		    		if(categoryNotNull)
		    			out.print(category.toUpperCase().equals(type.name().toUpperCase()) ? "selected" : "");
    	
    				%>><%out.print(CategoriaAnnuncio.getCapitalizedName(type.name())); %></option> 
    		<%
    	 	}
    	
    	
    	%>
      
      
  
  </select></li>
<br>
<li> <label for="sel1" style="text-align: left !important;">Prezzo</label>
    
         <div class="input-group">
      

<span class="input-group-addon" style="
    font-size: 0.8em;
    width: 2em !important;
">Min:</span>
      <input type="number" class="form-control" id="minPrice" name="minPrice" min="0" step="0.01" value="<%
      if(searchBean.getMinPrice() > 0)
    	  out.print(searchBean.getMinPrice());%>" <%
      out.print(loginBean.userIsNotLogged() ? "disabled" : "");%>><span class="input-group-addon">Eur</span>
    </div>

<div class="input-group" style="
    margin-top: 0.7em;
">
      

<span class="input-group-addon" style="
    font-size: 0.7em;
    width: 2em !important;
">Max:</span>
      <input type="number" class="form-control" id="maxPrice" name="maxPrice" min="0" step="0.01" value="<%
      out.print(searchBean.getMaxPrice() > 0 ? searchBean.getMaxPrice() : "");%>" <%
      out.print(loginBean.userIsNotLogged() ? "disabled" : "");%>><span class="input-group-addon">Eur</span>
    </div>


    
  </li>
        <br><li><label style="
    font-weight: 200 !important;
"><input type="checkbox" style="
    " name="extendToDescription" value="true" id="extendToDescription" <%
    if(searchBean.isChecked())
    		out.print("checked"); %> 
    		<%
      out.print(loginBean.userIsNotLogged() ? "disabled" : "");%>><span> Cerca anche nella descrizione</span></label></li>
        <br>
        <li class="center">
          <input type="hidden" value="" name="orderBy" id="orderBy">
        <button type="submit" name="search" id="search" class="btn btn-success btn-lg center" style="width: 100%;" <%
      out.print(loginBean.userIsNotLogged() ? "disabled" : "");%>>
    <span class="glyphicon glyphicon-search"></span> Search
</button></li>
<br><li><a onclick="customReset();" class="btn btn-warning btn-lg noHover" style="width: 100%;" <%
      out.print(loginBean.userIsNotLogged() ? "disabled" : "");%>>Reset
    </a></li>
      </ul>
      
    
			       			
    
      </form><br>
      
    </div>

    <div class="col-sm-10" style="
    /*background-color: rgba(60, 181, 213, 0.84);*/
    height: inherit;
    overflow-y: auto;
">
  <div class="container-fluid" style="
    /* max-width: 850px; */
  /*  background-color: rgba(60, 181, 213, 0.84);*/
    ">
    <h3 class="text-center"> </h3>
    
    
    
    
  <%
  	if (request.getParameter("search") != null)
	{  
		
		int result;
		
		if(loginBean.userIsLogged())
			result = searchBean.advanceSearch();
		else
			result = searchBean.basicSearch();
		
		if(result == -2)
		{
		%>	
			
			<div class="alert alert-danger" role="alert">
        	  <strong>Errore</strong> nel completare la richiesta. Riprovare più tardi.
       		</div>
			
		<%	
		}
		
		if(result == -1)
		{
		%>	
			
			<div class="alert alert-danger" role="alert">
        	  <strong>Errore:</strong> Hai inserito dei filtri di ricerca incompatibili o non consistenti.
       		</div>
			
		<%	
		}
		
		else
		{
	    %>
    
	    <ul class="list-group">
	      <li class="list-group-item">La tua ricerca ha prodotto  <span class="label label-success"><%out.print(result);%></span>  risultat<%out.print(result == 1 ? "o" : "i");%>. </li>
	    <%	if (result != 0)
	    	  {
	    	  %>
		       <li class="list-group-item">
			       <div class="jumbotron-fluid" style="margin-left: auto;height: 1.8em;">
						
			      			<button onclick="ordinaPrezzoDecrescente();" name="search" class="btn btn-danger" style="margin-left: auto;padding: 3px 10px 3px 10px;float: right;margin-right: 0.8em;">Prezzo 
			      					 <span class="	glyphicon glyphicon-arrow-down"></span>
			      			</button>
			      			<button onclick="ordinaPrezzoCrescente();" name="search" class="btn btn-success" style="margin-left: auto;padding: 3px 10px 3px 10px;float: right;margin-right: 0.8em;">Prezzo 
			       					<span class="	glyphicon glyphicon-arrow-up"></span>
			       			</button>
			       		 
			       	</div>
		       </li>
	       
	       <%
	       }
	       
	       %>
	       
	       
	       
	    </ul>
	    
	    <% 
	    
	    String orderBy = request.getParameter("orderBy");
	    
	    
	    
	    	if(orderBy != null)
	    	{	
	    		if(orderBy.equals("crescente"))
		    		searchBean.orderByPrezzoCrescente();
	    		
	    		if(orderBy.equals("decrescente"))
		    		searchBean.orderByPrezzoDecrescente();
	    		
	    		
	    	}
	    	
	    	
	    	
	    	int count =0; 
	    	boolean divAperto = false;
	    	
			for(AnnuncioBean annuncioBean : searchBean.getRisultati())
			{	
				
				
				
	    				if((count %4 ==0) || (count ==0)){
	    					
	    					divAperto = true;
	    					out.print("<div class=\"row text-center\">");
	    				}
	    %>				<div class="col-sm-3">
						        <div class="thumbnail" style="
						    padding: 0 0 15px 0;
						    border: none;
						    border-radius: 0;
						">
						          <img class="rounded-circle" src="<%out.print(annuncioBean.getFoto()); %>" alt="San Francisco" width="400" height="300" style="
						    /* border-radius: 50% !important; */
						">
						          
						
						<p class="text-center" style="
						    width: 90%;
						    margin-right: auto;
						    margin-left: auto;
						    margin-top: 0.8em;
						    font-weight: 100;
						    max-height: 6em !important;
						    font-style: italic;
						    font-stretch: condensed !important;
						    font-size: 1em !important;
						    text-align: right;
						    display: block;
						    color: rgba(105, 105, 105, 0.88);
						    text-shadow: 1px 0px rgba(105, 105, 105, 0.15);
						    padding-bottom: 0.2em;
						    letter-spacing: -0.04em;
						    ">
    
						    <span class="glyphicon glyphicon-tags" style="
						    padding-right: 1em;
						    vertical-align: middle;
						"></span><%out.print(annuncioBean.getCategoria().toUpperCase()); %></p>
						
						<p class="text-center block-with-text " style="
						    width: 90%;
						    text-align: justify;
						    display: block;
						    margin-right: auto;
						    margin-left: auto;
						    margin-top: 1.2em;
						    font-weight: 400;
						    font-size: 1.6em;
						    letter-spacing: -2px;
						    padding-bottom: 0.3em;
						"><%out.print(annuncioBean.getTitolo()); %></p>
						          <p class="text-center block-with-text " style="
						    width: 90%;
						    display: block;
						    margin-right: auto;
						    margin-left: auto;
						    margin-top: 2em;
						    font-weight: 100;
						    max-height: 6em !important;
						    font-style: italic;
						    font-stretch: condensed !important;
						    font-size: 1em !important;
						    ">
						    <%			out.print(annuncioBean.getDescrizione()); %>
						    </p>
						          <p style="
						    text-align: left;
						    /* margin-left: auto; */
						    margin-top: 3em;
						    font-size: 1.2em;
						    font-weight: 400;
						    /* margin-right: auto; */
						    /* display: inline-block; */
						    letter-spacing: 2px;
						    margin-left: 2em;
						">
						    Prezzo: <span style="
						    font-weight: 700;
						    margin-left: 1.5em;
						    color: #17b64d;
						    font-size: 1.3em;
						"> <%			out.print(annuncioBean.getPrezzo() +" "); %> <span class="glyphicon glyphicon-euro" style="
				    font-size: initial;
				    top: 0;
				    /* padding-left: 0.2em; */
				"></span></span>
						</p><p style="
						    text-align: left;
						    margin-left: 2em !important;
						    margin-top: 1.5em;
						    font-size: 1.2em;
						    font-weight: 400;
						    letter-spacing: 2px;
						">
						    Quantità: <span class="label label-warning" style="
						    margin-left: 2em;
						"><%			out.print(annuncioBean.getQuantità()); %></span>
						</p>
						
						<br><br>
						<a class="btn btn-info" style=" display: block; margin-left: 1.8em;margin-right: 1.8em;"
								href="VisualizzaDettagliAnnuncio.jsp?idAnnuncio=<%out.print(annuncioBean.getId());%>">Vai ai dettagli</a>
						
						        </div>
						      </div>
	      
	      <%
	      			count++;
	      			if((count %4 ==0) || (count ==0)){
						out.print("</div>");
						divAperto = false;
					}
	      			
	      
			}
			
			if(divAperto)
	  			out.print("</div>");
		
		
		}
	}
  
  		      
      
      %>
      
      
      
      
      
  
</div>
    </div>
  </div>
</div>

<footer class="container-fluid">
  <%@include file="footer.html"%>
</footer>



</body>
<script>
function customReset()
{
    document.getElementById("title").value = "";
    document.getElementById("title").placeholder = "";
    document.getElementById("category").value = "";
    document.getElementById("minPrice").value = "";
    document.getElementById("maxPrice").value = "";
    document.getElementById("extendToDescription").checked = false;
}

function ordinaPrezzoDecrescente()
{
	
	document.getElementById("orderBy").value = "decrescente";
	document.getElementById("search").click();
	
	
}

function ordinaPrezzoCrescente()
{
	
	document.getElementById("orderBy").value = "crescente";
	document.getElementById("search").click();
	
	
}


</script>
</html>

