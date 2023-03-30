import {Component, Input, OnInit} from '@angular/core';
import {ConstantPoolElementJsonFormat} from "../../../services/models/classFile";

@Component({
  selector: 'app-constant-pool',
  templateUrl: './constant-pool.component.html',
  styleUrls: ['./constant-pool.component.scss']
})
export class ConstantPoolComponent implements OnInit {

  @Input()
  public constantPool: ConstantPoolElementJsonFormat[] | undefined;

  ngOnInit() {
    console.log(this.constantPool)
  }


}
