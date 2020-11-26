package com.ayodkay.apps.suas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private enum class  GettingStartedContent(val titleResId: Int,val icon: Int) {
        ABRIGO(R.string.abrigo,R.drawable.ic_baseline_home_24),
        CREAS_LIST(R.string.creas_list,R.drawable.ic_baseline_list_24),
        CRAS_LOCATE(R.string.locate_cras,R.drawable.ic_baseline_search_24),
        CRAS_DEFINITION(R.string.oq_cras,R.drawable.ic_baseline_help_outline_24),
        CREAS_DEFINITION(R.string.oq_creas,R.drawable.ic_baseline_help_outline_24)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        get_started_recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = GettingStarted(GettingStartedContent.values())
        }
    }


    private inner class GettingStarted constructor(private val content:Array<GettingStartedContent>):
        RecyclerView.Adapter<GettingStarted.GettingStartedModel>(){

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):GettingStartedModel {
            return GettingStartedModel(LayoutInflater.from(parent.context).inflate(R.layout.get_started,parent,false))
        }

        override fun getItemCount(): Int  = content.size

        override fun onBindViewHolder(holder:GettingStartedModel, position: Int) {
            holder.bindGetStarted(content[position])
        }

        private inner class GettingStartedModel constructor(private var view: View): RecyclerView.ViewHolder(view){

            private val imageView : ImageView = view.findViewById(R.id.get_started_icon)
            private val textView : TextView = view.findViewById(R.id.get_started_text)

            fun bindGetStarted(content: GettingStartedContent){
                textView.setText(content.titleResId)
                imageView.setImageResource(content.icon)

                var descriptionTitle = ""
                var descriptionContent = ""


                view.setOnClickListener {

                    when(content.titleResId){
                        R.string.abrigo-> {
                            descriptionTitle   = getString(R.string.abrigo)
                            descriptionContent = getString(R.string.abrigo_description)
                        }
                        R.string.oq_cras-> {
                            descriptionTitle   = getString(R.string.oq_cras)
                            descriptionContent = getString(R.string.cras_description)
                        }
                        R.string.oq_creas-> {
                            descriptionTitle   = getString(R.string.oq_creas)
                            descriptionContent = getString(R.string.creas_description)
                        }
                    }
                    if (content.titleResId == R.string.abrigo||
                        content.titleResId == R.string.oq_cras||
                        content.titleResId == R.string.oq_creas){

                        startActivity(Intent(this@MainActivity,
                            DescriptionActivity::class.java)
                            .putExtra("descriptionTitle",descriptionTitle)
                            .putExtra("descriptionContent",descriptionContent))
                    }
                }
            }
        }

    }
}