import {Component} from '@angular/core';
import {CodeModel} from "@ngstack/code-editor";

@Component({
  selector: 'app-code-editor',
  templateUrl: './editor.component.html',
  styleUrls: ['./editor.component.scss']
})
export class EditorComponent {
  theme = 'vs-dark';

  codeModel: CodeModel = {
    language: 'java',
    uri: 'Main.java',
    value: `import com.example.insert.annotations.Block;
import com.example.insert.annotations.Jasm;

public class Main {

  @Jasm({
    @Block(start=11, end=15)
  })
  public static void main(String[] args) {
    /*
     getstatic 7
     bipush 8
     iconst_5
     invokestatic 13
     invokevirtual 19
    */
    System.out.println(sum(8, 5));
  }

  public static int sum(int a, int b) {
       return a+b;
  }
}
`
  };

  options = {
    contextmenu: true,
  };

  onCodeChanged(value: any) {
  }
}
