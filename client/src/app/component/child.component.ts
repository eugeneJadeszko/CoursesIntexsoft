import {Component} from '@angular/core';

@Component({
  selector: 'child-comp',
  templateUrl: '../templates/child.template.html',
  styleUrls: ['../styles/child.style.css']
})
export class ChildComponent {
  name = '';
}
