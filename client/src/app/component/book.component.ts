import {Component, Inject} from '@angular/core';
import Book from '../model/book';
import IBookService from '../service/ibook.service';

@Component({
    selector: 'book',
    templateUrl: '../templates/book.template.html'
})

export class BookComponent {
    books: Book[];

    constructor(@Inject('bookService') private bookService: IBookService) {
    }

    refresh() {
        this.books = [];
        this.bookService.findAll()
            .then(result => this.books = result)
            .catch((e: Error) => alert(e.message));
    }

    createBook(title: string, author: string) {
        this.bookService.createBook(title, author)
            .catch((e: Error) => alert(e.message));
    }

    deleteById(id: number) {
        this.bookService.deleteById(id)
            .catch((e: Error) => alert(e.message));
    }
}
