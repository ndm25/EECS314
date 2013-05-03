package com.smogdent.eecs314;


import java.io.File;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import mips.sim.Instruction;
import mips.sim.Memory;
import mips.sim.RegisterFile;
import mips.sim.Word;
import mips.sim.instructions.AddInstruction;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.annotation.TargetApi;
import android.app.DialogFragment;
import android.app.ListActivity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;


@TargetApi(Build.VERSION_CODES.JELLY_BEAN)
public class NewProgramActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_program);
        Bundle extras = getIntent().getExtras();
        String whatIsThisFile = extras.getString("chosenfile");
        
        if(!whatIsThisFile.equals("NEW_FILE")) {
        	//it's a saved file, have to actually load it
        	
        }
        else {
        	//do nothing; it's a new file
        }
        
        List<Instruction> instructions = new LinkedList<Instruction>();
        
        Memory memory = new Memory();
        RegisterFile regFile = new RegisterFile();
        
        instructions.add(new AddInstruction(memory, regFile, new Word(0)));
        
        Instruction[] insArr = (Instruction[]) instructions.toArray(new Instruction[0]);
        
        //TODO: actually fetch instructions that have been added
        ListAdapter adapter = new InstructionObjectArrayAdapter(this, insArr);
        this.setListAdapter(adapter);
        
        //clicky button listener 
        //DOES NOT and CANNOT do list item clicks
        //TODO: list click listener
        OnClickListener listener = new OnClickListener(){
            public void onClick(View view){
                if (view.getId() == R.id.newInstructionButton){
                    startActivity(new Intent(getApplicationContext(), CategoryListActivity.class));
                }
                else if(view.getId() == R.id.saveButton){
                	String state = Environment.getExternalStorageState();
                	
                	if(Environment.MEDIA_MOUNTED.equals(state)) {
                		//we can write to the media
                		String root = Environment.getExternalStorageDirectory().toString();
                		File directory = new File(root + "/com.smogdent.eecs314");
                		directory.mkdirs();
                		DialogFragment dialog = new SaveFileDialogFragment();
                		dialog.show(getFragmentManager(), "SaveFileDialogFragment");
                			
                	}
                }
            }
        };
        
        //list click listener
        OnItemClickListener listListener = new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                    int position, long id) {
                //TODO: make it do real things
                // selected item
                String instruction = ((TextView) view).getText().toString();
                // bundling level, instruction
                Intent intent = new Intent(getApplicationContext(), ItemDetailActivity.class);
                //intent.putExtra("insruction", (Serializable)insArr[position]);
                startActivity(intent);
            }
        };
        
        getListView().setOnItemClickListener(listListener);

        View instructionButton = findViewById(R.id.newInstructionButton);
        if(instructionButton != null){
            instructionButton.setOnClickListener(listener);
        }
        
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.new_instruction, menu);
        return true;
    }

}
