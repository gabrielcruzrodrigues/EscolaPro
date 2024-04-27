import { Component, Input, EventEmitter, Output } from '@angular/core';

@Component({
  selector: 'app-backdoor',
  templateUrl: './backdoor.component.html',
  styleUrls: ['./backdoor.component.scss']
})
export class BackdoorComponent {
  @Input() router: string = '';
}
