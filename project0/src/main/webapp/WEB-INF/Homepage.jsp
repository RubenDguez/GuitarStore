<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Revature Project 0 Home Page</title>
</head>
<body>
	<h1>Guitar Store</h1>
	<p>Guitar Store is an API created to manage the product side of
		your guitar store. If your are looking for an "all included API" for
		your small shop, look no more, with this API you will get all you need
		to start your front end development.</p>

	<h2>End-points</h2>
	<hr />
	<h3>
		<strong><code>/</code></strong>
	</h3>

	<ul>
		<li><strong>This is a GET request only endpoint</strong></li>
		<li>This is the starting "home endpoint" which shows all the
			endpoints implemented by the API. use that end point for a fast
			reference of the endpoints</li>
	</ul>
	<hr />

	<h3>
		<strong><code>/userlogin</code></strong>
	</h3>
	<ul>
		<li><strong>This is a POST request only endpoint</strong></li>
		<li>Use this endpoint to log in users.</li>
		<li>Need to provide the following key value pairs in the body of
			your request:</li>
		<ul>
			<li><code>username</code></li>
			<li><code>password</code></li>
		</ul>
		<li>Body example</li>
		<code> { "username":"john.doe", "password":"password" } </code>
		<li>password field is automatically converted to an MD5
			encryption format to provide a piece of mind in security.</li>
	</ul>

	<hr />

	<h3>
		<strong><code>/userlogout</code></strong>
	</h3>
	<ul>
		<li><strong>This is a POST request only endpoint</strong></li>
		<li>Use this endpoint to log out users</li>
		<li>No request-body necessary, if user was not logged in, the
			server will just re-new the session.</li>
	</ul>

	<hr>

	<h3>
		<strong><code>/user</code></strong>
	</h3>
	<ul>
		<li><strong>GET REQUEST</strong></li>
		<ul>
			<li>Returns all active users.</li>
			<li>User must be logged in as Administrator in order to get
				access to this endpoint.</li>
		</ul>

		<li><strong>POST REQUEST</strong></li>
		<ul>
			<li>Creates a new Employee user</li>
			<li>User must be logged in as Administrator in order to get
				access to this endpoint.</li>
			<li>Need the following key value pairs in the body of your
				request:</li>
			<ul>
				<li><code>uniqueID</code></li>
				<li><code>username</code></li>
				<li><code>username</code></li>
				<li><code>email</code></li>
				<li><code>password</code></li>
				<li><code>userType_UID</code></li>
			</ul>

			<li>Body example</li>
			<code> { "uniqueID": 0, "username":"alexis.dominguez",
				"email":"alexis.dominguez@yahoo.comm" "password":"alexisdominguez",
				"userType_UID": 2 } </code>
		</ul>

	</ul>

	<hr />

	<h3>
		<strong><code>/user/*</code></strong>
	</h3>

	<ul>
		<li><strong>GET REQUEST</strong></li>
		<ul>
			<li>Returns user by id</li>
			<li>User must be logged in as Administrator in order to get
				access to this endpoint.</li>
		</ul>
		<li><strong>PUT REQUEST</strong></li>
		<ul>
			<li>Updates the current user</li>
			<li>User must be logged in as Administrator in order to get
				access to this endpoint.</li>
			<li>Need the following key value pairs in the body of your
				request:</li>
			<ul>
				<li><code>uniqueID</code></li>
				<li><code>username</code></li>
				<li><code>email</code></li>
				<li><code>password</code></li>
				<li><code>userType_UID</code></li>
			</ul>
			<li>Body example:</li>
			<code> { "uniqueID": 0, "username":"alexis.dominguez",
				"email":"alexis.dominguez@yahoo.comm" "password":"alexisdominguez",
				"userType_UID": 2 } </code>
		</ul>

		<li><strong>DELETE REQUEST</strong></li>
		<ul>
			<li>Deletes an user by id</li>
			<li>User must be logged in as Administrator in order to get
				access to this endpoint.</li>
		</ul>
	</ul>

	<hr />

	<h3>
		<strong><code>/sign/up</code></strong>
	</h3>
	<ul>
		<li>Use this endpoint to create customers. These users have no
			access to any administrative endpoint.</li>
		<li>This is a POST request only endpoint</li>
		<li>Need the following key value pairs in the body of your
			request:</li>
		<ul>
			<li><code>username</code></li>
			<li><code>email</code></li>
			<li><code>password</code></li>
		</ul>
		<li>Body example</li>
		<code> { "username":"alexis.dominguez",
			"email":"alexis.dominguez@yahoo.comm" "password":"alexisdominguez", }
		</code>
	</ul>

	<hr />

	<h3>
		<strong><code>/sign/out</code></strong>
	</h3>
	<ul>
		<li>Use this enpoint to allow customers to opt-out of the system.</li>
		<li>No request-body necessary.</li>
	</ul>

	<hr />

	<h3>
		<strong><code>/sign/recover</code></strong>
	</h3>
	<ul>
		<li>Use this enpoint to 'recover' access to the system for a
			previously opted-out customer.</li>
		<li>Need the following key value pairs in the body of your
			request:</li>
		<ul>
			<li><code>username</code></li>
			<li><code>password</code></li>
		</ul>
		<li>Body example</li>
		<code> { "email":"alexis.dominguez@yahoo.comm"
			"password":"alexisdominguez", } </code>
	</ul>

	<hr />

	<h3>
		<strong><code>/product</code></strong>
	</h3>
	<ul>
		<li>Retrieves all products from database.</li>
	</ul>

	<hr />

	<h3>
		<strong><code>/product/*</code></strong>
	</h3>
	<ul>
		<li>Regrieves on specific product by id</li>
	</ul>

	<hr />

	<h3>
		<strong><code>/product/brand/*</code></strong>
	</h3>
	<ul>
		<li>Retrieves products with matching Brand Id</li>
	</ul>

	<hr />

	<h3>
		<strong><code>/product/department/*</code></strong>
	</h3>
	<ul>
		<li>Retrieves products with matching Department Id</li>
	</ul>

	<hr />

	<h2>Actually working on</h2>
	<p>For our next version we've planned to fully implement the
		following</p>
	<ul>
		<li>Review Section with average</li>
		<li>CRUD functionalities for Brand, Category, Condition,
			Department, Features, PremiumGear, Specifications and Style</li>
	</ul>

	<hr />
	<h2>Contributors</h2>
	<h4>Revature 2011 Java/React team</h4>


</body>
</html>