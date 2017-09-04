import {Component, Inject} from '@angular/core';
import Board from '../model/board';
import IBoardService from '../service/iboard.service';

@Component({
  selector: 'board',
  templateUrl: '../templates/board.template.html'
})

export class BoardComponent {
  boards: Board[];

  constructor(@Inject('boardService') private boardService: IBoardService) {
  }

  refresh(): void {
    this.boardService.findAll()
      .then(result => this.boards = result)
      .catch((e: Error) => alert(e.message));
  }

  createBoard(numberOfBoard: number) {
    this.boardService.createBoard(numberOfBoard)
      .catch((e: Error) => alert(e.message));
  }

  deleteById(id: number) {
    this.boardService.deleteById(id)
      .catch((e: Error) => alert(e.message));
  }
}
