import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { TipsComponent } from './tips/tips.component';
import {TipsRoutingModule} from "./tips-routing.module";



@NgModule({
  declarations: [
    TipsComponent
  ],
  imports: [
    CommonModule,
    TipsRoutingModule
  ]
})
export class TipsModule { }
