package com.sample.simpsonsviewer.models

data class MetaX(
    val attribution: Any,
    val blockgroup: Any,
    val created_date: Any,
    val description: String,
    val designer: Any,
    val dev_date: Any,
    val dev_milestone: String,
    val developer: List<DeveloperX>,
    val example_query: String,
    val id: String,
    val is_stackexchange: Any,
    val js_callback_name: String,
    val live_date: Any,
    val maintainer: MaintainerX,
    val name: String,
    val perl_module: String,
    val producer: Any,
    val production_state: String,
    val repo: String,
    val signal_from: String,
    val src_domain: String,
    val src_id: Int,
    val src_name: String,
    val src_options: SrcOptionsX,
    val src_url: Any,
    val status: String,
    val tab: String,
    val topic: List<String>,
    val unsafe: Int
)