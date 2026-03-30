import { motion } from 'motion/react';
import { Github, ExternalLink } from 'lucide-react';
import { Section } from '../components/Section';
import { Card } from '../components/Card';
import { PROJECTS } from '../constants';

export const Portfolio = () => {
  return (
    <Section id="portfolio" title="Featured Projects" subtitle="A selection of my recent work and open-source contributions.">
      <div className="grid gap-8 md:grid-cols-2 lg:grid-cols-3">
        {PROJECTS.map((project, index) => (
          <motion.div
            key={project.title}
            initial={{ opacity: 0, y: 20 }}
            whileInView={{ opacity: 1, y: 0 }}
            viewport={{ once: true }}
            transition={{ duration: 0.5, delay: index * 0.1 }}
          >
            <Card className="group h-full overflow-hidden p-0">
              <div className="aspect-video overflow-hidden">
                <img 
                  src={project.image} 
                  alt={project.title}
                  className="h-full w-full object-cover transition-transform duration-500 group-hover:scale-110"
                  referrerPolicy="no-referrer"
                />
              </div>
              <div className="p-6">
                <h3 className="text-xl font-bold text-zinc-900 dark:text-white">{project.title}</h3>
                <p className="mt-2 text-sm text-zinc-600 dark:text-zinc-400">
                  {project.description}
                </p>
                <div className="mt-4 flex flex-wrap gap-2">
                  {project.tech.map((t) => (
                    <span 
                      key={t} 
                      className="rounded-full bg-zinc-100 px-3 py-1 text-xs font-medium text-zinc-600 dark:bg-zinc-800 dark:text-zinc-400"
                    >
                      {t}
                    </span>
                  ))}
                </div>
                <div className="mt-6 flex items-center gap-4">
                  <a 
                    href={project.github}
                    className="flex items-center gap-1 text-sm font-medium text-zinc-600 hover:text-zinc-900 dark:text-zinc-400 dark:hover:text-white"
                  >
                    <Github size={18} /> Code
                  </a>
                  <a 
                    href={project.live}
                    className="flex items-center gap-1 text-sm font-medium text-zinc-600 hover:text-zinc-900 dark:text-zinc-400 dark:hover:text-white"
                  >
                    <ExternalLink size={18} /> Demo
                  </a>
                </div>
              </div>
            </Card>
          </motion.div>
        ))}
      </div>
    </Section>
  );
};
