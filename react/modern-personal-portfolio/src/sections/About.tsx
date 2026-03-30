import { motion } from 'motion/react';
import { Section } from '../components/Section';
import { Card } from '../components/Card';
import { SKILLS, EXPERIENCE } from '../constants';

export const About = () => {
  return (
    <Section id="about" title="About Me" subtitle="A blend of technical expertise and creative vision.">
      <div className="grid gap-12 lg:grid-cols-2">
        {/* Skills */}
        <motion.div
          initial={{ opacity: 0, x: -20 }}
          whileInView={{ opacity: 1, x: 0 }}
          viewport={{ once: true }}
          transition={{ duration: 0.6 }}
        >
          <h3 className="mb-8 text-2xl font-bold text-zinc-900 dark:text-white">Technical Skills</h3>
          <div className="grid gap-4 sm:grid-cols-2">
            {SKILLS.map((skill) => (
              <Card key={skill.name} className="flex items-center gap-4 p-4">
                <div className="rounded-lg bg-zinc-100 p-2 dark:bg-zinc-800">
                  <skill.icon size={24} className="text-zinc-900 dark:text-white" />
                </div>
                <div>
                  <p className="font-semibold text-zinc-900 dark:text-white">{skill.name}</p>
                  <div className="mt-1 h-1 w-24 rounded-full bg-zinc-100 dark:bg-zinc-800">
                    <div 
                      className="h-full rounded-full bg-zinc-900 dark:bg-white" 
                      style={{ width: `${skill.level}%` }}
                    />
                  </div>
                </div>
              </Card>
            ))}
          </div>
        </motion.div>

        {/* Experience */}
        <motion.div
          initial={{ opacity: 0, x: 20 }}
          whileInView={{ opacity: 1, x: 0 }}
          viewport={{ once: true }}
          transition={{ duration: 0.6 }}
        >
          <h3 className="mb-8 text-2xl font-bold text-zinc-900 dark:text-white">Experience</h3>
          <div className="space-y-8">
            {EXPERIENCE.map((exp, index) => (
              <div key={index} className="relative pl-8 before:absolute before:left-0 before:top-2 before:h-full before:w-px before:bg-zinc-200 dark:before:bg-zinc-800 last:before:h-0">
                <div className="absolute left-[-4px] top-2 h-2 w-2 rounded-full bg-zinc-900 dark:bg-white" />
                <p className="text-sm font-medium text-zinc-500">{exp.period}</p>
                <h4 className="mt-1 text-lg font-bold text-zinc-900 dark:text-white">{exp.role}</h4>
                <p className="text-sm font-medium text-zinc-600 dark:text-zinc-400">{exp.company}</p>
                <p className="mt-2 text-zinc-600 dark:text-zinc-400">{exp.description}</p>
              </div>
            ))}
          </div>
        </motion.div>
      </div>
    </Section>
  );
};
