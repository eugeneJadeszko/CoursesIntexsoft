import {Component} from '@angular/core';
import {TranslateService} from '@ngx-translate/core';

@Component({
  selector: 'my-app',
  templateUrl: './templates/app.template.html',
  styleUrls: ['./styles/app.style.css']
})
export class AppComponent {

  constructor(private translate: TranslateService) {
    translate.setDefaultLang('en');
  }

  switchLanguage(language: string) {
    this.translate.use(language);
  }
}
