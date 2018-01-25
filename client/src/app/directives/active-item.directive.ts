import {Directive, ElementRef, HostListener} from '@angular/core';

@Directive({
  selector: '[appActiveItem]'
})
export class ActiveItemDirective {

  constructor(el: ElementRef) { }

  @HostListener('click') onClick(){
  }
}
