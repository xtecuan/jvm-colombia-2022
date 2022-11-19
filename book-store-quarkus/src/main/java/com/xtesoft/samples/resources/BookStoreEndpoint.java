package com.xtesoft.samples.resources;

import com.xtesoft.samples.entities.Book;
import com.xtesoft.samples.services.BookService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@RequestScoped
@Path("books")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BookStoreEndpoint {
    @Inject
    BookService bookService;

    @GET
    public Response getAll() {
        return Response.ok(bookService.getAll()).build();
    }

    @GET
    @Path("{id}")
    public Response getBook(@PathParam("id") Long id) {
        Book book = bookService.findById(id);

        return Response.ok(book).build();
    }

    @POST
    public Response create(Book book) {
        bookService.create(book);
        return Response.ok().build();
    }

    @PUT
    @Path("{id}")
    public Response update(@PathParam("id") Long id, Book book) {
        Book updateBook = bookService.findById(id);

        updateBook.setIsbn(book.getIsbn());
        updateBook.setDescription(book.getDescription());
        updateBook.setLanguage(book.getLanguage());
        updateBook.setPages(book.getPages());
        updateBook.setPrice(book.getPrice());
        updateBook.setPublisher(book.getPublisher());
        updateBook.setTitle(book.getTitle());

        bookService.update(updateBook);

        return Response.ok().build();
    }

    @DELETE
    @Path("{id}")
    public Response delete(@PathParam("id") Long id) {
        Book getBook = bookService.findById(id);
        bookService.delete(getBook);
        return Response.ok().build();
    }
}
