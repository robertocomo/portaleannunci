<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ page import="bean.AnnuncioBean"%>
<%@ page import="bean.GestioneAnnuncioBean"%>
<%@ page import="model.TipologiaUtente"%> 


<jsp:useBean id="loginBean" scope="session" class="bean.LoginBean" />
<jsp:useBean id="gestioneAnnuncioBean" scope="page" class="bean.GestioneAnnuncioBean" />

<%	if(loginBean.userIsNotLogged() || !(loginBean.getTipo().equals(TipologiaUtente.PRODUTTORE)))	
	{ 
		response.sendRedirect("./Login.jsp");	
		
	}


//	GestioneAnnuncioBean newGestioneAnnuncioBean = new GestioneAnnuncioBean();
//	request.setAttribute("gestioneAnnuncioBean", newGestioneAnnuncioBean);

%>

<!-- HTML 5 -->
<!DOCTYPE html>
<html><head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="../../favicon.ico">

    <title>I tuoi Annunci</title>

    <!-- Bootstrap core CSS -->
    <link href="./css/bootstrap.min.css" rel="stylesheet">

    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <link href="./css/ie10-viewport-bug-workaround.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="./css/jumbotron-narrow.css" rel="stylesheet">

   
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
<style>

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





    @media (min-width: 768px){
.container {
    max-width: 970px !important; 
        }
    }
    .card {
    position: relative;
    display: -webkit-box;
    display: -webkit-flex;
    display: -ms-flexbox;
    display: flex;
    -webkit-box-orient: vertical;
    -webkit-box-direction: normal;
    -webkit-flex-direction: column;
    -ms-flex-direction: column;
    flex-direction: column;
    background-color: #fff;
    border: 1px solid rgba(0,0,0,.125);
    border-radius: .25rem;
}

.card-block {
    -webkit-box-flex: 1;
    -webkit-flex: 1 1 auto;
    -ms-flex: 1 1 auto;
    flex: 1 1 auto;
    padding: 1.25rem;
}

.card-title {
    margin-bottom: .75rem;
}

.card-img-top {
    border-top-right-radius: calc(.25rem - 1px);
    border-top-left-radius: calc(.25rem - 1px);
}
    </style>

</head>
    		  
 <body style="
    font-family: -apple-system,system-ui,BlinkMacSystemFont,&quot;Segoe UI&quot;,Roboto,&quot;Helvetica Neue&quot;,Arial,sans-serif;
">

    <div class="container">
      <jsp:include page="header.jsp"/>

      <div class="jumbotron-fluid">
      <%
	      	int result = gestioneAnnuncioBean.getAllAnnunci(loginBean.getId());
     		
      		if(result > 0)
      		{
      
      		    
	   	    	int count =0; 
	   	    	boolean divAperto = false;
	   	    	
	   			for(AnnuncioBean annuncioBean : gestioneAnnuncioBean.getRisultati())
	   			{	

	   	    				if((count %3 ==0) || (count ==0)){
	   	    					
	   	    					divAperto = true;
	   	    					out.print("<div class=\"row\" style=\"margin-bottom: 2em;\">");
	   	    				}
   	    
      			%>

      						<!-- PRIMO ELEMENTO -->
							  <div class="col-sm-4">
							    <div class="card" style="
							    /* max-height: 6em; */
							">
							  <div style="
							    height: 7em !important;
							    overflow: hidden;
							"><img class="card-img-top" src="<%out.print(annuncioBean.getFoto()); %>" alt="Card image cap" style="
							    /* height: 5em; */
							    /* image-rendering: pixelated; */
							    /* display: block; */
							    width: 100%;
							    /* height: auto; */
							    /* background-size: cover; */
							    /* overflow: hidden; */
							    "></div>
							  <div class="card-block">
							  
							  <%
							  		if(annuncioBean.isAnnuncioPrivato())
							  		{
							  		%>	
									  			<div class="row" style="padding: 0 1em;">
									  	        <div class="col-sm-12" style="
									  	    text-align: center;
									  	    letter-spacing: 6px;
									  	    font-size: 0.7em;
									  	    border: 1px solid #DDD;
									  	    background-color: #d9534f;
									  	    color: #FFF;
									  	    border-radius: 0.8rem;
									  	    padding: 0.5em 0;
									  	    font-weight: 400;
									  	    margin-bottom: 1.5em;
									  	">Privato</div>
									  	    </div>
							  			
							  			
							  		<%	
							  		}
							  
							  
							  %>
							  
							  
							    <h4 class="card-title block-with-text" style="
							    font-size: 1.8em;
							    width: 95%;
							    text-align: initial;
								"> <%out.print(annuncioBean.getTitolo()); %></h4>
							    
							    
							    </br>
							    <p class="block-with-text" style="
								    width: 95%;
								    font-style: oblique;
								    font-stretch: condensed;
								    font-weight: 200;
								    font-size: 0.9em;
								"><%out.print(annuncioBean.getDescrizione()); %>
							    </p>
							    <div class="row" style="
								    margin-top: 2em !important;
								    font-size: 1.3em;">
									  <div class="col-sm-4">Prezzo:</div>
									  <div class="col-sm-8" style="font-weight: 500;text-align: left;"><%out.print(annuncioBean.getPrezzo()); %> Eur</div>
								</div>
								
								<div class="row" style="
									    margin-top: 0.8em !important;
									    font-size: 1.3em;
									    margin-bottom: 2.2em;">
										  <div class="col-sm-5">Quantità:</div>
										  <div class="col-sm-7" style="text-align: left;font-weight: 500;"><%out.print(annuncioBean.getQuantità()); %></div>
								</div>
							    
							    
							 <a href="GestioneAnnuncio.jsp?idAnnuncio=<%out.print(annuncioBean.getId());%>" class="btn btn-success" style="display: block;">Apri annuncio</a>
							    
							    
							   
							  <!--  <form class="form-horizontal" action="GestioneAnnuncio.jsp" name="myform" method="POST">
	      				 <input type="hidden" value="<%out.print(annuncioBean.getId());%>" name="idAnnuncio" id="idAnnuncio">
	      					 <button type="submit" name="search" class="btn btn-primary" >Prezzo crescente</button></form> -->
							  
							  </div>
							</div>
							  </div>

      			<%
      		  
		    			count++;
		    			if((count %3 ==0) || (count ==0)){
							out.print("</div>");
							divAperto = false;
						}
    			
    
				}
	   			
	   			if(divAperto)
		  			out.print("</div>");
		
	   			
      	}
      		
      		else
      		{
      	%>
      			
			<div class="alert alert-warning" role="alert">
				<strong>Sembra che tu non abbia ancora alcun annuncio !</strong>
				 <a href="AggiungiAnnuncio.jsp" class="alert-link">Clicca qui per crearne uno nuovo.</a>
							  
						</div>
       	<%
       		} 
       %>
      
      


      </div>

      

      <footer class="footer">
       <%@include file="footer.html"%>
      </footer>

    </div> <!-- /container -->


    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <script src="./js/ie10-viewport-bug-workaround.js"></script>
     <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="./js/bootstrap.min.js"></script>
  

</body></html>