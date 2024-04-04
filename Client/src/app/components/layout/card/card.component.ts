import { Component, Input } from '@angular/core';

@Component({
  selector: 'card',
  templateUrl: './card.component.html',
  styleUrls: ['./card.component.scss']
})
export class CardComponent {
  @Input() icon: string = '';
  @Input() text: string = '';
  @Input() backgroundColor: string = '';
  @Input() router: string = '';
}
