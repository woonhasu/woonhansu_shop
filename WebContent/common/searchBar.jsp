<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<div class="search">
	<form action="controller?command=search" method="post">
		<input type="text" name="name" required>
		<input type="submit" value="검색">
	</form>
	<div class="category">
		<a href="controller?command=getProductPage">All</a> 
		<a href="controller?command=category&category=outer">Outer</a>
		<a href="controller?command=category&category=top">Top</a>
		<a href="controller?command=category&category=bottom">Bottom</a>
		<a href="controller?command=category&category=setup">Setup</a>
		<a href="controller?command=category&category=bag">Bag</a>
	</div>
</div>