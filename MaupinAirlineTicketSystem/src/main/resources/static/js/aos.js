/*!
 * AOS - Animate On Scroll Library
 * Minimal offline version
 */
(function () {
  var AOS = {
    duration: 600,
    easing: 'ease-out-cubic',
    once: true,
    offset: 80,
    disable: false,
    init: function (options) {
      if (options) {
        if (options.duration) this.duration = options.duration;
        if (options.once !== undefined) this.once = options.once;
        if (options.offset !== undefined) this.offset = options.offset;
        if (options.disable !== undefined) this.disable = options.disable;
      }
      if (this.disable) return;

      var self = this;
      var elements = document.querySelectorAll('[data-aos]');

      // Set duration from options on each element
      elements.forEach(function (el) {
        el.style.transitionDuration = self.duration + 'ms';
      });

      // IntersectionObserver for performance
      if ('IntersectionObserver' in window) {
        var observer = new IntersectionObserver(function (entries) {
          entries.forEach(function (entry) {
            if (entry.isIntersecting) {
              entry.target.classList.add('aos-animate');
              if (self.once) {
                observer.unobserve(entry.target);
              }
            } else if (!self.once) {
              entry.target.classList.remove('aos-animate');
            }
          });
        }, {
          rootMargin: '0px 0px -' + self.offset + 'px 0px',
          threshold: 0.1
        });

        elements.forEach(function (el) {
          observer.observe(el);
        });
      } else {
        // Fallback: just show everything
        elements.forEach(function (el) {
          el.classList.add('aos-animate');
        });
      }
    },
    refresh: function () {
      // no-op for compatibility
    }
  };

  window.AOS = AOS;

  document.addEventListener('DOMContentLoaded', function () {
    AOS.init();
  });
})();
