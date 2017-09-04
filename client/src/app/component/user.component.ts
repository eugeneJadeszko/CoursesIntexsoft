import {Component, Inject} from '@angular/core';
import User from '../model/user';
import IUserService from '../service/iuser.service';

@Component({
  selector: 'user',
  templateUrl: '../templates/user.template.html'
})

export class UserComponent {
  users: User[];

  constructor(@Inject('userService') private userService: IUserService) {
  }

  refresh(): void {
    this.userService.findAll()
      .then(result => this.users = result)
      .catch((e: Error) => alert(e.message));
  }

  createUser(name: string) {
    this.userService.createUser(name)
      .catch((e: Error) => alert(e.message));
  }

  deleteById(id: number) {
    this.userService.deleteById(id)
      .catch((e: Error) => alert(e.message));
  }
}
