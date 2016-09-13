<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<li><a href="<spring:url value="/"/>">Home</a></li>
<li><a href="<spring:url value="/customer/"/>">Customer</a></li>
<li><a href="<spring:url value="/catalog"/>">Catalog</a></li>
<li><a href="<spring:url value="/cart/"/>">Login</a></li>
<li>
	<div class="dropdown">
    	<button class="btn btn-default dropdown-toggle" type="button" data-toggle="dropdown"
    	aria-haspopup="true" aria-expanded="true">Customer
    	<span class="caret"></span></button>
    	<ul class="dropdown-menu">
<!--       		<li class="dropdown-header">Shopping Cart</li> -->
     		 <li><a href="#">Online Purchase</a></li>
      		<li><a href="#">Retrive Saved Cart</a></li>
      		<li><a href="#">Review Orders</a></li>
     
      		<li class="divider"></li>
<!--         	<li class="dropdown-header">Exit</li> -->
        	<li><a href="#">Exit</a></li>
      </ul>
  </div>
</li>
<li>
<div class="dropdown">
    	<button class="btn btn-default dropdown-toggle" type="button" data-toggle="dropdown"
    	aria-haspopup="true" aria-expanded="true">Adminstrator
    	<span class="caret"></span></button>
    	
    	<ul class="dropdown-menu">
<!--       		<li class="dropdown-header">Shopping Cart</li> -->
     		 <li><a href="#">Maintain Catalog</a></li>
      		<li><a href="#">Maintain Product</a></li>
      		
     
      		<li class="divider"></li>
<!--         	<li class="dropdown-header">Exit</li> -->
        	<li><a href="#">Exit</a></li>
      </ul>
  </div>
</li>