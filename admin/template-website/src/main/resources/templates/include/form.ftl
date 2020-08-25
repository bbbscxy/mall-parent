<script>
    function submitForm() {
        if($('#inputForm').valid()){
            //loading
            var index = window.parent.layer.load(1, {
                shade: [0.3,'#fff'], //0.1透明度的白色背景
            });
            $.ajax({
                url: $("#inputForm").attr("action"),
                type: "POST",
                data: $('#inputForm').serialize(),
                success: function(result){
                    window.parent.layer.close(index);
                    window.parent.layer.msg(result.msg, { time: 2000 });
                    if(result.code == "SUCCESS"){
                        window.parent.layer.closeAll();
                        window.frames.parent['iframe'].contentWindow.search();
                    }
                }
            })
        }
    }
</script>