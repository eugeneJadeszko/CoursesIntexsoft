import {Injectable} from '@angular/core';
import Book from '../model/book';
import IBookService from './ibook.service';
import {Http} from '@angular/http';
import 'rxjs/add/operator/toPromise';

const BOOK_ALL = '/library/api/book/all';
const BOOK_CREATE = '/library/api/book/add';
const BOOK_DEL_BY_ID = '/library/api/book/del/';

@Injectable()
export default class BookService implements IBookService {

    constructor(private  http: Http) {
    }

    book: Book;

    findAll(): Promise<Book[]> {
        return this.http.get(BOOK_ALL)
            .toPromise()
            .then(response => response.json());
    }

    createBook(title: string, author: string): Promise<Book> {
        this.book = new Book();
        this.book.tittle = title;
        this.book.author = author;
        return this.http.post(BOOK_CREATE, this.book)
            .toPromise()
            .then(response => response.json());
    }

    deleteById(id: number): Promise<Book> {
        return this.http.get(BOOK_DEL_BY_ID + id)
            .toPromise()
            .then(response => response.json());
    }
}
