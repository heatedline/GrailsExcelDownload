package example.grails

import groovy.transform.CompileStatic

@CompileStatic
class BookService {

    List<Book> findAll() {

        Book book1 = new Book()
        book1.setIsbn("1491950358")
        book1.setName("Building Microservices")

        Book book2 = new Book()
        book2.setIsbn("1680502395")
        book2.setName("Release It!")

        Book book3 = new Book()
        book3.setIsbn("0321601912")
        book3.setName("Continuous Delivery:")


        [
                book1, book2, book3
        ]
    }
}
