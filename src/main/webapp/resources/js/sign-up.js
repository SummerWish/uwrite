$(document).ready(function ()
{

  setTimeout(function () {
    $('.uwrite-animation.hide').each(function (i) {
      var self = $(this);
      setTimeout(function () {
        self.removeClass('hide');
      }, i * 100);
    });
  }, 500);

  var validationRules = {
    email: {
      identifier  : 'email',
      rules: [{
        type   : 'empty',
        prompt : 'Please enter an e-mail'
      }, {
        type   : 'email',
        prompt : 'Please enter a valid e-mail'
      }]
    },
    nickname: {
      identifier:'nickname',
      rules: [{
        type:'empty',
        prompt:'Please pick up a nick name'
      }]
    },
    password: {
      identifier: 'password',
      rules: [{
        type: 'empty',
        prompt: 'Please enter a password'
      }, {
        type: 'length[3]',
        prompt: 'Password needs to be at least 3 characters long'
      }]
    },       
    comfirmPassword: {
      identifier: 'confirmPassword',
      rules: [{
        type: 'match[password]',
        prompt: 'Password don\'t match'
      }]
    },
    isAgree: {
      identifier:'isAgree',
      rules: [{
        type:'checked',
        prompt: 'Your must agree to the terms and conditions'
      }]
    }
  };

  $('.ui.dropdown').dropdown({
    on: 'hover'
  });

  $('.ui.form').form(validationRules, {
    on: 'blur',
    inline: 'true',
    transition: 'fade up',
    duration: 300,
    onSuccess: function () {
      $(this).addClass('loading');
      var form = $(this).form('get values');
      $.ajax({
        url: '/signup',
        type: 'POST',
        data: form
      }).done(function (data) {
        
        $('.uwrite-animation').each(function (i) {
          var self = $(this);
          setTimeout(function () {
            self.addClass('hide-out');
          }, i * 70);
        });
        setTimeout(function () {
          window.location = '/';
        }, ($('.uwrite-animation').length + 1) * 100)

      }).fail(function (jqXHR) {
        var err = jqXHR.responseJSON;
        if (err.error) {
          $('.modal').find('.description').text(err.message);
          $('.modal').modal('show');
        }
      }).always(function () {
        $('.ui.form').removeClass('loading');
      });
      return false;
    }
  });
  
  $('.ui.checkbox')
    .checkbox()
  ;
});
